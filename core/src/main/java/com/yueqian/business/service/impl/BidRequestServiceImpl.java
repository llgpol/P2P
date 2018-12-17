package com.yueqian.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqian.base.domain.Account;
import com.yueqian.base.domain.Userinfo;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.service.IAccountService;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.util.BigConst;
import com.yueqian.base.util.BitStatesUtils;
import com.yueqian.base.util.DateUtil;
import com.yueqian.base.util.UserContext;
import com.yueqian.business.domain.BidRequest;
import com.yueqian.business.domain.BidRequestAuditHistory;
import com.yueqian.business.mapper.BidRequestMapper;
import com.yueqian.business.mapper.BidrequestaudithistoryMapper;
import com.yueqian.business.query.BidRequestQueryObject;
import com.yueqian.business.service.IBidRequestService;
import com.yueqian.business.util.CalculatetUtil;

@Service
public class BidRequestServiceImpl implements IBidRequestService {

	@Autowired
	private BidRequestMapper bidRequestMapper;

	@Autowired
	private IUserinfoService userinfoService;

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private BidrequestaudithistoryMapper historyMapper;

	@Override
	public void update(BidRequest bidRequest) {
		int ret = bidRequestMapper.updateByPrimaryKey(bidRequest);
		if (ret == 0) {
			throw new RuntimeException("乐观锁失败 bidRequest：" + bidRequest.getId());
		}

	}

	@Override
	public boolean canApplyBidRequest(Long logininfoId) {
		// 得到指定用户
		Userinfo userinfo = userinfoService.get(logininfoId);
		// 判断：1.基本资料；2.实名认证； 3.视频认证；4.风控分数达到指定值；5.没有借款在流程当中
		if (userinfo != null && userinfo.getIsBasicInfo() && userinfo.getIsRealAuth() && userinfo.getIsVedioAuth()
				&& userinfo.getScore() >= BigConst.BASE_BORROW_SCORE && !userinfo.getHasBidRequestInProcess()) {
			return true;
		}
		return false;
	}

	@Override
	public void apply(BidRequest bidRequest) {
		Account account = accountService.getCurrent();
		// 首先要满足申请条件
		if (this.canApplyBidRequest(UserContext.getLogininfo().getId())
				&& bidRequest.getBidRequestAmount().compareTo(BigConst.SMALLEST_BIDREQUEST_AMOUNT) >= 0
				&& bidRequest.getBidRequestAmount().compareTo(account.getRemainBorrowLimit()) <= 0// 1.系统最小借款金额<= 借款金额
																									// <= 剩余信用额度
				&& bidRequest.getCurrentRate().compareTo(BigConst.SMALLEST_CURRENT_RATE) >= 0
				&& bidRequest.getCurrentRate().compareTo(BigConst.MAX_CURRENT_RATE) <= 0// 2.5<=利息<=20
				&& bidRequest.getMinBidAmount().compareTo(BigConst.SMALLEST_BID_AMOUNT) >= 0// 3.最小投标金额>=系统最小投标金额
		) {
			// =====借款申请
			// 1.创建一个新的bidRequest，设置相关参数
			BidRequest br = new BidRequest();
			br.setBidRequestAmount(bidRequest.getBidRequestAmount());
			br.setCurrentRate(bidRequest.getCurrentRate());
			br.setDescription(bidRequest.getDescription());
			br.setDisableDays(bidRequest.getDisableDays());;
			br.setMinBidAmount(bidRequest.getMinBidAmount());
			br.setReturnType(bidRequest.getReturnType());
			br.setTitle(bidRequest.getTitle());
			br.setMonthes2Return(bidRequest.getMonthes2Return());

			// 2.设置相关值
			br.setApplyTime(new Date());
			br.setBidRequestState(BigConst.BIDREQUEST_STATE_PUBLISH_PENDING);
			br.setCreateUser(UserContext.getLogininfo());
			br.setTotalRewardAmount(CalculatetUtil.calTotalInterest(bidRequest.getBidRequestType(),
					bidRequest.getBidRequestAmount(), bidRequest.getCurrentRate(), bidRequest.getMonthes2Return()));
			br.setPublishTime(new Date());
			
			// 3.保存
			bidRequestMapper.insert(br);
			
			// 4.给借款人添加一个状态码
			Userinfo userinfo = userinfoService.getUserinfo();
			userinfo.addstate(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
			userinfoService.update(userinfo);
		}
	}

	@Override
	public PageResult query(BidRequestQueryObject qo) {
		int count = bidRequestMapper.queryForCount(qo);
		if (count>0) {
			List<BidRequest> list = bidRequestMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void publihAudit(Long id, String remark, int state) {
		//查出bidrequest
		BidRequest br = bidRequestMapper.selectByPrimaryKey(id);
		//判断状态
		if (br != null && br.getBidRequestState() == BigConst.BIDREQUEST_STATE_PUBLISH_PENDING) {
			//创建一个审核历史对象
			BidRequestAuditHistory history = new BidRequestAuditHistory();
			history.setApplier(br.getCreateUser());
			history.setApplyTime(br.getApplyTime());
			history.setAuditor(UserContext.getLogininfo());
			history.setAuditType(BidRequestAuditHistory.PUBLISH_AUDIT);
			history.setAuditTime(new Date());
			history.setRemark(remark);
			history.setState(state);
			history.setBidRequestId(br.getId());
			historyMapper.insert(history);
			
			if (state == BidRequestAuditHistory.STATE_AUDIT) {
				//如果审核通过：修改标的状态，设置风控意见；
				br.setBidRequestState(BigConst.BIDREQUEST_STATE_BIDDING);
				br.setDisableDate(DateUtils.addDays(new Date(), br.getDisableDays()));
				br.setNote(remark);
			}else {
				//如果审核失败：修改标的状态，用户要去掉状态码
				br.setBidRequestState(BigConst.BIDREQUEST_STATE_PUBLISH_REFUSE);
				Userinfo applier = userinfoService.get(br.getCreateUser().getId());
				applier.removeState(BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
				userinfoService.update(applier);
			}
			update(br);
		}
		
		
	}

	@Override
	public List<BidRequestAuditHistory> listAuditHistoryByBidRequest(Long id) {
		
		return historyMapper.listByBidRequest(id);
	}

	@Override
	public BidRequest get(Long id) {
		
		return bidRequestMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BidRequest> listIndex(int size) {
		BidRequestQueryObject qo = new BidRequestQueryObject();
		qo.setBidRequestStates(new int[] {BigConst.BIDREQUEST_STATE_BIDDING,
				BigConst.BIDREQUEST_STATE_PAYING_BACK,
				BigConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK});
		qo.setCurrentPage(1);
		qo.setPageSize(size);
		qo.setOrderBy("bidRequestState");
		qo.setOrderType("asc");
		qo.setBidRequestState(null);
		return bidRequestMapper.query(qo);
	}

}

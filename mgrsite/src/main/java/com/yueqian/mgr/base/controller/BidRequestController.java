package com.yueqian.mgr.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqian.base.domain.UserFile;
import com.yueqian.base.domain.Userinfo;
import com.yueqian.base.query.UserFileQueryObject;
import com.yueqian.base.service.IRealAuthService;
import com.yueqian.base.service.IUserFileService;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.util.BigConst;
import com.yueqian.base.util.JSONResult;
import com.yueqian.business.domain.BidRequest;
import com.yueqian.business.query.BidRequestQueryObject;
import com.yueqian.business.service.IBidRequestService;

/**
 * 借款相关controller
 * @author Administrator
 *
 */
@Controller
public class BidRequestController {

	@Autowired
	private IBidRequestService bidRequestService;
	
	@Autowired
	private IUserinfoService userinfoService;
	
	@Autowired
	private IRealAuthService realAuthService;
	
	@Autowired
	private IUserFileService userFileService;
	
	@RequestMapping("bidrequest_publishaudit_list")
	public String bidrequestPublishauditList(@ModelAttribute("qo") BidRequestQueryObject qo, Model model) {
		qo.setBidRequestState(BigConst.BIDREQUEST_STATE_PUBLISH_PENDING);
		model.addAttribute("pageResult",bidRequestService.query(qo));
		return "bidrequest/publish_audit";
	}
	
	/**
	 * 发表前审核
	 * @return
	 */
	@RequestMapping("bidrequest_publishaudit")
	@ResponseBody
	public JSONResult bidrequestPublishAudit(Long id, String remark, int state ) {
		bidRequestService.publihAudit(id,remark,state);
		return new JSONResult(); 
	}
	
	/**
	 * 后台查询借款详情
	 */
	@RequestMapping("borrow_info")
	public String borrowInfoDetail(Long id, Model model) {
		BidRequest bidRequest = bidRequestService.get(id);
		Userinfo userinfo = userinfoService.get(bidRequest.getCreateUser().getId());
		model.addAttribute("bidRequest", bidRequest);
		boolean isRealAuth = userinfo.getIsRealAuth();
		model.addAttribute("userInfo", userinfo);
		//这个标的审核历史
		model.addAttribute("audits", bidRequestService.listAuditHistoryByBidRequest(id));
		//借款人实名认证信息
		model.addAttribute("realAuth", realAuthService.get(userinfo.getRealAuthId()));
		//该借款人的风控资料
		UserFileQueryObject qo = new UserFileQueryObject();
		qo.setApplierId(userinfo.getId());
		qo.setPageSize(-1);
		qo.setState(UserFile.STATE_AUDIT);
		model.addAttribute("userFiles", userFileService.queryForList(qo));
		
		return "bidrequest/borrow_info";	
	}
	
	
	
	
}

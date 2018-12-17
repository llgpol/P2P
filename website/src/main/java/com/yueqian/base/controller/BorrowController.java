package com.yueqian.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueqian.base.domain.Account;
import com.yueqian.base.domain.Logininfo;
import com.yueqian.base.domain.Userinfo;
import com.yueqian.base.query.UserFileQueryObject;
import com.yueqian.base.service.IAccountService;
import com.yueqian.base.service.IRealAuthService;
import com.yueqian.base.service.IUserFileService;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.util.BigConst;
import com.yueqian.base.util.UserContext;
import com.yueqian.business.domain.BidRequest;
import com.yueqian.business.service.IBidRequestService;

/**
 * 关于借款的controller
 * @author Administrator
 *
 */
@Controller
public class BorrowController {

	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private IUserinfoService userinfoService;
	
	@Autowired
	private IBidRequestService bidRequestService;
	
	@Autowired
	private IRealAuthService realAuthService;
	
	@Autowired
	private IUserFileService userFileService;
	
	
	@RequestMapping("borrow")
	public String borrowIndex(Model model) {
		Logininfo logininfo = UserContext.getLogininfo();
		if (logininfo==null) {
			return "redirect:borrow.html";
		}else {
			Account account = accountService.getCurrent();
			model.addAttribute("account", account);
			model.addAttribute("userinfo", userinfoService.getUserinfo());
			model.addAttribute("creditBorrowScore", BigConst.BASE_BORROW_SCORE);
			return "borrow";
		}	
	}
	
	/**
	 * 导向借款申请页面
	 */
	@RequestMapping("borrowInfo")
	public String borrowInfo(Model model) {
		Long id = UserContext.getLogininfo().getId();
		if (bidRequestService.canApplyBidRequest(id)) {
			model.addAttribute("minBidRequestAmount", BigConst.SMALLEST_BIDREQUEST_AMOUNT);//最小借款金额
			model.addAttribute("minBidAmount", BigConst.SMALLEST_BID_AMOUNT);//最小投标金额
			model.addAttribute("account", accountService.getCurrent());
			return "borrow_apply";
		}else {
			return "borrow_apply_result";
		}
	}
	
	@RequestMapping("borrow_apply")
	public String borrowApply(BidRequest bidRequest) {
		bidRequestService.apply(bidRequest);
		return "redirect:/borrowInfo.do";
	}
	
	@RequestMapping("borrow_info")
	public String borrowInfoDetail(Long id, Model model) {
		BidRequest bidRequest = bidRequestService.get(id);
		if (bidRequest != null) {
			Userinfo applier = userinfoService.get(bidRequest.getCreateUser().getId());
			model.addAttribute("realAuth", realAuthService.get(applier.getRealAuthId()));
			UserFileQueryObject qo = new UserFileQueryObject();
			qo.setApplierId(applier.getId());
			qo.setPageSize(-1);
			qo.setCurrentPage(1);
			model.addAttribute("userFiles", userFileService.queryForList(qo));
			model.addAttribute("bidRequest", bidRequest);
			model.addAttribute("userInfo", applier);
			if (UserContext.getLogininfo() != null) {
				if (UserContext.getLogininfo().getId().equals(applier.getId())) {
					model.addAttribute("self", true);
				}else {
					model.addAttribute("self", false);
					model.addAttribute("account", accountService.getCurrent());
				}
				
			}else {
				model.addAttribute("self", false);
			}
			
		}
		
		return "borrow_info";
	}

}

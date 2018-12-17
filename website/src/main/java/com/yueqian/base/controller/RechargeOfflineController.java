package com.yueqian.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqian.base.util.JSONResult;
import com.yueqian.base.util.RequireLogin;
import com.yueqian.base.util.UserContext;
import com.yueqian.business.domain.RechargeOffline;
import com.yueqian.business.query.RechargeOfflineQueryObjcect;
import com.yueqian.business.service.IPlatformBankInfoService;
import com.yueqian.business.service.IRechargeOfflineService;

/**
 * 前台的线下充值
 * @author Administrator
 *
 */
@Controller
public class RechargeOfflineController {

	@Autowired
	private IPlatformBankInfoService platformBankInfoService;
	
	@Autowired
	private IRechargeOfflineService rechargeOfflineService;
	
	/**
	 * 导向线下充值页面
	 * @param model
	 * @return
	 */
	@RequireLogin
	@RequestMapping("recharge")
	public String recharge(Model model) {
		model.addAttribute("banks", platformBankInfoService.listAll());
		return "recharge";
	}
	
	/**
	 * 提交线下冲值单
	 * @param model
	 * @return
	 */
	@RequireLogin
	@RequestMapping("recharge_save")
	@ResponseBody
	public JSONResult rechargeSave(RechargeOffline rechargeOffline) {
		rechargeOfflineService.apply(rechargeOffline);
		return new JSONResult();
	}
	
	@RequireLogin
	@RequestMapping("recharge_list")
	public String query(@ModelAttribute("qo") RechargeOfflineQueryObjcect qo,Model model) {
		qo.setApplierId(UserContext.getLogininfo().getId());
		model.addAttribute("pageResult", rechargeOfflineService.query(qo));
		return "recharge_list";
	}
	
	
	
	
	
	
}

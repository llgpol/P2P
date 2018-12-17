package com.yueqian.mgr.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqian.base.util.JSONResult;
import com.yueqian.business.domain.PlatformBankInfo;
import com.yueqian.business.query.PlatformBankInfoQueryObject;
import com.yueqian.business.service.IPlatformBankInfoService;

@Controller
public class PlatformBankInfoController {

	@Autowired
	private IPlatformBankInfoService platformBankInfoService;
	
	@RequestMapping("companyBank_list")
	private String platformBankInfoList(
			@ModelAttribute("qo") PlatformBankInfoQueryObject qo, Model model) {
		model.addAttribute("pageResult", platformBankInfoService.query(qo));
		return "platformbankinfo/list";
	}
	
	@RequestMapping("companyBank_update")
	@ResponseBody
	public JSONResult update(PlatformBankInfo bankInfo) {
		platformBankInfoService.updateOrSave(bankInfo);
		return new JSONResult();
	}
	
	
}

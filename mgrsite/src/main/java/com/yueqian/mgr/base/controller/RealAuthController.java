package com.yueqian.mgr.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqian.base.query.RealAuthQueryObject;
import com.yueqian.base.service.IRealAuthService;
import com.yueqian.base.util.JSONResult;

/**
 * 实名验证相关controller
 * @author Administrator
 *
 */
@Controller
public class RealAuthController {

	@Autowired
	private IRealAuthService realAuthService;
	
	@RequestMapping("realAuth")
	public String realAuth(@ModelAttribute RealAuthQueryObject qo,Model model) {
		model.addAttribute("pageResult", realAuthService.query(qo));
		return "realAuth/list";
	}
	
	@RequestMapping("realAuth_audit")
	@ResponseBody
	public JSONResult realAuthAudit(Long id, String remark, int state) {
		realAuthService.audit(id,remark,state);
		return new JSONResult();
	}
	
	
}

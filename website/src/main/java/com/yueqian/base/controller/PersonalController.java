package com.yueqian.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ModelAndViewMethodReturnValueHandler;

import com.yueqian.base.domain.Logininfo;
import com.yueqian.base.service.IAccountService;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.util.JSONResult;
import com.yueqian.base.util.RequireLogin;
import com.yueqian.base.util.UserContext;
/**
 * 个人中心
 * @author Administrator
 *
 */
@Controller
public class PersonalController {
	
	@Autowired
	private IUserinfoService userinfoService;
	
	@Autowired
	private IAccountService accountService;
	
	@RequireLogin
	@RequestMapping("personal")
	public String personalCenter(Model model){
		
		Logininfo logininfo = UserContext.getLogininfo();
		model.addAttribute("userinfo" , userinfoService.get(logininfo.getId()));
		model.addAttribute("account",accountService.get(logininfo.getId()));
		return "personal";
	}
	
	@RequireLogin
	@RequestMapping("bindPhone")
	@ResponseBody
	public JSONResult bindPhone(String phoneNumber,String verifyCode){
		JSONResult jsonResult =new JSONResult();
		
		try {
			userinfoService.bindPhone(phoneNumber,verifyCode);
		} catch (RuntimeException re) {
			jsonResult.setMsg(re.getMessage());
			jsonResult.setSuccess(false);
		}
		
		return jsonResult;
	}
	
	@RequireLogin
	@RequestMapping("sendEmail")
	@ResponseBody
	public JSONResult sendEmail(String email){
		JSONResult jsonResult =new JSONResult();
		
		try {
			userinfoService.sendEmail(email);
		} catch (RuntimeException re) {
			jsonResult.setMsg(re.getMessage());
			jsonResult.setSuccess(false);
		}
		
		return jsonResult;
	}
	
	@RequestMapping("bindEmail")
	public String bindEmail(String key , Model model) {
		try {
			userinfoService.bindEmail(key);
			model.addAttribute("success", true);
		} catch (RuntimeException re) {
			model.addAttribute("success", false);
			model.addAttribute("msg", re.getMessage());
		}
		return "checkmail_result";
	}
}

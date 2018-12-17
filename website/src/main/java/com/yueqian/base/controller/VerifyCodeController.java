package com.yueqian.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqian.base.service.IVerifyCodeService;
import com.yueqian.base.util.JSONResult;

@Controller
public class VerifyCodeController {
	
	@Autowired
	private IVerifyCodeService verifyCodeService;
	
	/**
	 * 发送验证码
	 * @param phoneNumber
	 * @return
	 */
	@RequestMapping("/sendVerifyCode")
	@ResponseBody
	public JSONResult sendVerifyCode(String phoneNumber) {
		JSONResult jsonResult = new JSONResult();
		try {
			verifyCodeService.sendVerifyCode(phoneNumber);
		} catch (RuntimeException re) {
			jsonResult.setMsg(re.getMessage());
			jsonResult.setSuccess(false);
		}
		return jsonResult;
	}
	
}

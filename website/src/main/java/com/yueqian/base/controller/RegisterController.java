package com.yueqian.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.yueqian.base.domain.Logininfo;
import com.yueqian.base.service.ILogininfoService;
import com.yueqian.base.util.JSONResult;

@Controller
public class RegisterController {

	@Autowired
	private ILogininfoService logininfoService;

	/**
	 * 检验用户名是否存在
	 * 
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("checkUserName")
	public boolean checkUserName(String username) {

		return !logininfoService.checkUserName(username);
	}

	/**
	 * 注册
	 * 
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("register")
	public JSONResult register(String username, String password) {
		JSONResult jsonResult = new JSONResult();
		try {
			logininfoService.register(username, password);
		} catch (RuntimeException e) {
			e.printStackTrace();
			jsonResult.setSuccess(false);
			jsonResult.setMsg(e.getMessage());
		}

		return jsonResult;
	}

	/**
	 * 登录
	 * 
	 * @param username
	 * @return
	 */
	@ResponseBody
	@RequestMapping("login")
	public JSONResult login(String username, String password, HttpServletRequest request) {
		JSONResult jsonResult = new JSONResult();
		String ip = request.getRemoteAddr();
		Logininfo logininfo = logininfoService.login(username, password, ip ,Logininfo.USER_CLIENT);
		if (logininfo == null) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("用户名或密码错误");
		}
		return jsonResult;
	}

}

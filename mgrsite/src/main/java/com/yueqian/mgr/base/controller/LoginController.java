package com.yueqian.mgr.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqian.base.domain.Logininfo;
import com.yueqian.base.service.ILogininfoService;
import com.yueqian.base.util.JSONResult;

/**
 * 后台登录
 * @author Administrator
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private ILogininfoService logininfoService;
	
	/**
	 * 后台登录
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public JSONResult login(String username , String password , HttpServletRequest request) {
		JSONResult jsonResult = new JSONResult();
		Logininfo logininfo = logininfoService.login(username, password, request.getRemoteAddr(), Logininfo.USER_MANAGER);
		if (logininfo==null) {
			jsonResult.setSuccess(false);
			jsonResult.setMsg("用户名或密码错误");
		}
		return jsonResult;
	}
	
	@RequestMapping("index")
	public String index() {
		return "main";
	}
}

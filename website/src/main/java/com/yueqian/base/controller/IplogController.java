package com.yueqian.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueqian.base.query.IplogQueryObject;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.service.IIplogService;
import com.yueqian.base.util.RequireLogin;
import com.yueqian.base.util.UserContext;

@Controller
public class IplogController {
	
	@Autowired
	private IIplogService iplogService;
	
	//自动把qo注入到model属性，取名为qo
	@RequireLogin
	@RequestMapping("ipLog")
	public String iplogList(@ModelAttribute("qo") IplogQueryObject qo,Model model) {
		qo.setUsername(UserContext.getLogininfo().getUsername());
		PageResult pageResult = iplogService.query(qo);	
		model.addAttribute("pageResult", pageResult);
		return "iplog_list";
	}
}

package com.yueqian.mgr.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueqian.base.query.IplogQueryObject;
import com.yueqian.base.service.IIplogService;

@Controller
public class IplogController {
	
	@Autowired
	private IIplogService iplogservice;
	
	@RequestMapping("ipLog")
	public String iplog(@ModelAttribute("qo") IplogQueryObject qo,Model model ){
		model.addAttribute("pageResult", iplogservice.query(qo));
		return "ipLog/list";
	}
}

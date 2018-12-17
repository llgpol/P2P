package com.yueqian.mgr.base.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqian.base.query.VedioAuthQueryObject;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.service.IVedioAuthService;
import com.yueqian.base.util.JSONResult;

/**
 * 视频审核相关controller
 * @author Administrator
 *
 */
@Controller
public class VedioAuthController {

	@Autowired
	private IVedioAuthService vedioAuthService;
	
	@Autowired
	private IUserinfoService userinfoService;
	
	/**
	 * 视频审核查询
	 * @param qo
	 * @param model
	 * @return
	 */
	@RequestMapping("vedioAuth")
	public String vedioAuth(@ModelAttribute VedioAuthQueryObject qo, Model model) {
		model.addAttribute("pageResult", vedioAuthService.query(qo));
		return "vedioAuth/list";
	}
	
	/**
	 * 
	 */
	@RequestMapping("vedioAuth_audit")
	@ResponseBody
	public JSONResult vedioAuthAudit(Long loginInfoValue, String remark, int state ) {
		vedioAuthService.audit(loginInfoValue,remark,state);
		return new JSONResult();
	}
	
	/**
	 * 用于视频验证查询的自动补全
	 */
	@RequestMapping("vedioAuth_autocomplate")
	@ResponseBody
	public List<Map<String, Object>> autoComplate(String keyword) {
		return userinfoService.autoComplate(keyword);
	}
	
	
	
	
}

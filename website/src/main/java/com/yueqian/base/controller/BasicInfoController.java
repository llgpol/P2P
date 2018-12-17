package com.yueqian.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqian.base.domain.Userinfo;
import com.yueqian.base.service.ISystemDictionaryService;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.util.JSONResult;
import com.yueqian.base.util.RequireLogin;

@Controller
public class BasicInfoController {

	@Autowired
	private IUserinfoService userinfoService;
	
	@Autowired
	private ISystemDictionaryService systemDictionaryService;
	
	@RequireLogin
	@RequestMapping("basicInfo")
	public String basicInfo(Model model) {
		//得到当前用户
		model.addAttribute("userinfo", userinfoService.getUserinfo());
		//添加所有下拉菜单相关内容
		model.addAttribute("educationBackgrounds", systemDictionaryService.listByParentSn("educationBackground"));
		model.addAttribute("incomeGrades", systemDictionaryService.listByParentSn("incomeGrade"));
		model.addAttribute("marriages", systemDictionaryService.listByParentSn("marriage"));
		model.addAttribute("kidCounts", systemDictionaryService.listByParentSn("kidCount"));
		model.addAttribute("houseConditions", systemDictionaryService.listByParentSn("houseCondition"));
		return "userInfo";
	}
	
	@RequireLogin
	@RequestMapping("basicInfo_save")
	@ResponseBody
	public JSONResult basicInfosave(Userinfo userinfo) {
		userinfoService.updateBasicInfo(userinfo);
		return new JSONResult();
	}
	
	
	
	
	
}

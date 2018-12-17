package com.yueqian.mgr.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqian.base.domain.SystemDictionary;
import com.yueqian.base.domain.SystemDictionaryItem;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.query.SystemDictionaryQueryObject;
import com.yueqian.base.service.ISystemDictionaryService;
import com.yueqian.base.util.JSONResult;

@Controller
public class SystemDictionaryController {

	@Autowired
	private ISystemDictionaryService systemDictionaryService;
	
	@RequestMapping("systemDictionary_list")
	public String systemDictionaryList(@ModelAttribute("qo") SystemDictionaryQueryObject qo,Model model) {
		PageResult pageResult = systemDictionaryService.queryDics(qo);
		model.addAttribute("pageResult",pageResult);
		return "systemdic/systemDictionary_list";
	}
	
	@RequestMapping("systemDictionary_update")
	@ResponseBody
	public JSONResult systemDictionaryUpdate(SystemDictionary systemDictionary) {
		systemDictionaryService.updateOrSaveDics(systemDictionary);
		return new JSONResult();
	}
	
	@RequestMapping("systemDictionaryItem_list")
	public String systemDictionaryItemList(@ModelAttribute("qo") SystemDictionaryQueryObject qo,Model model) {
		PageResult pageResult = systemDictionaryService.queryItem(qo);
		model.addAttribute("pageResult",pageResult);
		model.addAttribute("systemDictionaryGroups", systemDictionaryService.list());
		return "systemdic/systemDictionaryItem_list";
	}
	
	@RequestMapping("systemDictionaryItem_update")
	@ResponseBody
	public JSONResult systemDictionaryItemUpdate(SystemDictionaryItem item) {
		systemDictionaryService.updateOrSaveItem(item);
		return new JSONResult();
	}
}

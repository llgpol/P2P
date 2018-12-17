package com.yueqian.mgr.base.controller;

import java.util.concurrent.atomic.LongAccumulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yueqian.base.query.UserFileQueryObject;
import com.yueqian.base.service.IUserFileService;
import com.yueqian.base.util.JSONResult;

@Controller
public class UserFileController {

	@Autowired
	private IUserFileService userFileService;
	
	@RequestMapping("userFileAuth")
	public String userFileAuthList(@ModelAttribute("qo") UserFileQueryObject qo, Model model) {
		model.addAttribute("pageResult", userFileService.query(qo));
		return "userFileAuth/list";
	}
	
	@RequestMapping("userFile_audit")
	@ResponseBody
	public JSONResult audit(Long id, int score, String remark, int state) {
		userFileService.audit(id, score, remark, state);
		return new JSONResult();
	}
	
}

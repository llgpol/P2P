package com.yueqian.base.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.yueqian.base.domain.UserFile;
import com.yueqian.base.service.ISystemDictionaryService;
import com.yueqian.base.service.IUserFileService;
import com.yueqian.base.util.JSONResult;
import com.yueqian.base.util.UploadUtil;
import com.yueqian.base.util.UserContext;

/**
 * 风控资料相关
 * @author Administrator
 *
 */
@Controller
public class UserFileController {

	@Autowired
	private IUserFileService userFileService;
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private ISystemDictionaryService systemDictionaryService;
	
	@RequestMapping("userFile")
	public String userFile(Model model,HttpServletRequest request) {
		List<UserFile> list = userFileService.listFilesByHasType(UserContext.getLogininfo().getId(),false);
		//有没有选择的用户文件类型，设置数据字典，并往userFiles_commit
		if (list.size()>0) {
			model.addAttribute("userFiles", list);
			model.addAttribute("fileTypes", systemDictionaryService.listByParentSn("riskManagementType"));
			return "userFiles_commit";
		}else {
			//选择所有该用户的风控文件
			//往userfile	
			model.addAttribute("sessionId", request.getSession().getId());
			list = userFileService.listFilesByHasType(UserContext.getLogininfo().getId(),true);
			model.addAttribute("userFiles", list);
			return "userFiles";
		}
		
		
		
	}
	
	@RequestMapping("userFileUpload")
	@ResponseBody
	public void userFileUpload(MultipartFile file) {
		String basePath = servletContext.getRealPath("/upload");
		String fileName = UploadUtil.upload(file, basePath);
		fileName = "/upload/" + fileName;
		userFileService.apply(fileName);
	}
	
	@RequestMapping("userFile_selectType")
	@ResponseBody
	public JSONResult userFileSelectType(Long[] fileType,Long[] id ) {
		if (fileType != null && id !=null && fileType.length == id.length) {
			userFileService.batchUpdateFileType(id, fileType);
		}
		return new JSONResult();
	}
	
	
	
}

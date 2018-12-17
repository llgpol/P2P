package com.yueqian.base.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yueqian.base.domain.RealAuth;
import com.yueqian.base.domain.Userinfo;
import com.yueqian.base.service.IRealAuthService;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.util.JSONResult;
import com.yueqian.base.util.RequireLogin;
import com.yueqian.base.util.UploadUtil;

/**
 * 实名验证controller
 * @author Administrator
 *
 */
@Controller
public class RealAuthController {

	@Autowired
	private IUserinfoService userinfoService;
	
	@Autowired
	private IRealAuthService realAuthService;
	
	@Autowired
	private ServletContext servletContext;
	
	@RequireLogin
	@RequestMapping("realAuth")
	public String realAuth(Model model) {
		
		//1.得到当前userinfo
		Userinfo userinfo = userinfoService.getUserinfo();
		//2.如果用户已经实名验证
		if (userinfo.getIsRealAuth()) {
			//根据userinfo中的realAuthId得到实名验证对象，并放到model;
			model.addAttribute("realAuth", realAuthService.get(userinfo.getRealAuthId()));
			//auditing = false
			model.addAttribute("auditing", false);
			model.addAttribute("userinfo", userinfoService.getUserinfo());
			return "realAuth_result";
		}else {
			//3.如果用户没有实名验证
			if (userinfo.getRealAuthId()!=null) {
				//1.userinfo上有realAuthId，auditing = true
				model.addAttribute("auditing", true);
				return "realAuth_result";
			}else {
				//2.userinfo上没有realAuthId，跳转到realAuth
				return "realAuth";
			}	
		}	
	}
	
	/**
	 * 千万不要加requestLogin
	 * @param file
	 */
	@RequestMapping("realAuth_upload")
	@ResponseBody
	public String realAuthUpload(MultipartFile file) {
		//先得到basepath
		String basePath = servletContext.getRealPath("/upload");
		String fileName = UploadUtil.upload(file, basePath);
		return "/upload/"+fileName;
	}
	
	/**
	 * 申请实名认证
	 * @param realAuth
	 * @return
	 */
	
	@RequireLogin
	@RequestMapping("realAuth_save")
	@ResponseBody
	public JSONResult realAuthSave(RealAuth realAuth) {
		realAuthService.apply(realAuth);
		return new JSONResult();
	}
	
	
}

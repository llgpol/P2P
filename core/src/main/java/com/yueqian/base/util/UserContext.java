package com.yueqian.base.util;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.SessionAttributesHandler;

import com.yueqian.base.domain.Logininfo;
import com.yueqian.base.vo.VerifyCodeVO;

/**
 * 用于存放当前用户的上下文
 * @author Administrator
 *
 */
public class UserContext {
	public static final String USER_IN_SESSION = "logininfo";
	public static final String VERIFYCODE_IN_SESSION = "verifyCode";
	
	public static HttpSession getSession() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();			
	}
	
	public static void putLogininfo(Logininfo logininfo) {
		getSession().setAttribute(USER_IN_SESSION, logininfo);
	}
	
	public static Logininfo getLogininfo() {
		return (Logininfo)getSession().getAttribute(USER_IN_SESSION);
	}
	
	public static void putVerifyCode(VerifyCodeVO verifyCodeVO) {
		getSession().setAttribute(VERIFYCODE_IN_SESSION, verifyCodeVO);
	}
	
	public static VerifyCodeVO getVerifyCode() {
		return (VerifyCodeVO)getSession().getAttribute(VERIFYCODE_IN_SESSION);
	}
}

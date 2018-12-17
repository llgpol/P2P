package com.yueqian.base.domain;

import java.util.Date;

public class Iplog extends BaseDomain{
	
	public static final int STATE_SUCCESS = 1;
	public static final int STATE_FAILED = 0;
	
	private String ip;
	private Date loginTime;
	private String userName;
	private int state;
	private int userType;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStateDisplay() {
		return state==STATE_SUCCESS?"登录成功":"登录失败";
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public String getUserTypeDisplay() {
		return userType==Logininfo.USER_CLIENT?"前端用户":"后台用户";
	}
}

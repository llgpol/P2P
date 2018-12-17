package com.yueqian.base.query;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;

import com.yueqian.base.util.DateUtil;



public class IplogQueryObject extends QueryObject{

	private Date beginTime;
	private Date endTime;
	private int state=-1;
	private String username;
	private int userType;
	
	public Date getBeginTime() {
		return beginTime;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime==null?null:DateUtil.endOfDay(endTime);
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUsername() {
		return StringUtils.hasLength(username)?username:null;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
}

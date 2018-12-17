package com.yueqian.base.vo;
/**
 * 存储验证码相关内容，这个对象是放在session中的
 * @author Administrator
 *
 */

import java.util.Date;

public class VerifyCodeVO {
	private String verifyCode;
	private String phoneNumber;
	private Date lastSendTime;
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getLastSendTime() {
		return lastSendTime;
	}
	public void setLastSendTime(Date lastSendTime) {
		this.lastSendTime = lastSendTime;
	}
	
	
}

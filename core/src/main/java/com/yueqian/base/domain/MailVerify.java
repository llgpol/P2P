package com.yueqian.base.domain;

import java.util.Date;

/**
 * 邮箱验证实体类
 * @author Administrator
 *
 */
public class MailVerify extends BaseDomain{
	
	private Long userinfoId;
	private String email;
	private String uuid;
	private Date sendDate;
	public Long getUserinfoId() {
		return userinfoId;
	}
	public void setUserinfoId(Long userinfoId) {
		this.userinfoId = userinfoId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	
	
}

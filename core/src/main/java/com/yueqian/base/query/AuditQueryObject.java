package com.yueqian.base.query;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.yueqian.base.util.DateUtil;

/**
 * 基本审核查询对象
 * @author Administrator
 *
 */
public class AuditQueryObject extends QueryObject {

	protected int state = -1;
	protected Date beginTime;
	protected Date endTime;
	
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
	
}

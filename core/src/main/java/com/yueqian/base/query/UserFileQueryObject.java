package com.yueqian.base.query;

/**
 * 风控资料查询对象
 * @author Administrator
 *
 */
public class UserFileQueryObject extends AuditQueryObject{

	private Long applierId;

	public Long getApplierId() {
		return applierId;
	}

	public void setApplierId(Long applierId) {
		this.applierId = applierId;
	}
	
	
}

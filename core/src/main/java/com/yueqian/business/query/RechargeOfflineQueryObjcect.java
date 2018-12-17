package com.yueqian.business.query;

import com.yueqian.base.query.AuditQueryObject;

/**
 * 线下充值查询
 * @author Administrator
 *
 */
public class RechargeOfflineQueryObjcect extends AuditQueryObject {
	
	private Long applierId;

	public Long getApplierId() {
		return applierId;
	}

	public void setApplierId(Long applierId) {
		this.applierId = applierId;
	}
	
	
}

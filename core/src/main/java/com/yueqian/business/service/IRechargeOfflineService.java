package com.yueqian.business.service;

import com.yueqian.base.query.PageResult;
import com.yueqian.business.domain.RechargeOffline;
import com.yueqian.business.query.RechargeOfflineQueryObjcect;

/**
 * 线下充值
 * @author Administrator
 *
 */
public interface IRechargeOfflineService {

	/**
	 * 提交线下充值单
	 */
	void apply(RechargeOffline rechargeOffline);
	
	PageResult query(RechargeOfflineQueryObjcect qo);
}

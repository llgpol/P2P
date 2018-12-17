package com.yueqian.base.service;

import com.yueqian.base.domain.RealAuth;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.query.RealAuthQueryObject;

/**
 * 申请审核服务
 * @author Administrator
 *
 */
public interface IRealAuthService {

	RealAuth get(Long id);

	/**
	 * 实名认证申请
	 * @param realAuth
	 */
	void apply(RealAuth realAuth);
	
	/**
	 * 申请审核分页
	 * @param qo
	 * @return
	 */
	PageResult query(RealAuthQueryObject qo);

	/**
	 * 实名认证审核
	 * @param id
	 * @param remark
	 * @param state
	 */
	void audit(Long id, String remark, int state);
}

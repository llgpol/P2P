package com.yueqian.business.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqian.base.query.PageResult;
import com.yueqian.base.util.UserContext;
import com.yueqian.business.domain.RechargeOffline;
import com.yueqian.business.mapper.RechargeOfflineMapper;
import com.yueqian.business.query.RechargeOfflineQueryObjcect;
import com.yueqian.business.service.IRechargeOfflineService;

@Service
public class RechargeOfflineServiceImpl implements IRechargeOfflineService{

	@Autowired
	private RechargeOfflineMapper rechargeOfflineMapper;
	
	@Override
	public void apply(RechargeOffline recharge) {
		recharge.setApplier(UserContext.getLogininfo());
		recharge.setApplyTime(new Date());
		recharge.setState(RechargeOffline.STATE_NORMAL);
		
		rechargeOfflineMapper.insert(recharge);
	}

	@Override
	public PageResult query(RechargeOfflineQueryObjcect qo) {
		int count = rechargeOfflineMapper.queryByCount(qo);
		if (count > 0) {
			List<RechargeOffline> list = rechargeOfflineMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}
	
	
}

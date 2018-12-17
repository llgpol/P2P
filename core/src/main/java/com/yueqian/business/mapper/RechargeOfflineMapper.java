package com.yueqian.business.mapper;

import com.yueqian.business.domain.RechargeOffline;
import com.yueqian.business.query.RechargeOfflineQueryObjcect;

import java.util.List;

public interface RechargeOfflineMapper {

    int insert(RechargeOffline record);

    RechargeOffline selectByPrimaryKey(Long id);

    int updateByPrimaryKey(RechargeOffline record);
    
    int queryByCount(RechargeOfflineQueryObjcect qo);
    
    List<RechargeOffline> query(RechargeOfflineQueryObjcect qo);
}
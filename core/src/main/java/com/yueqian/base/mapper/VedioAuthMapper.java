package com.yueqian.base.mapper;

import com.yueqian.base.domain.VedioAuth;
import com.yueqian.base.query.VedioAuthQueryObject;

import java.util.List;

public interface VedioAuthMapper {
	void insert(VedioAuth vedioAuth);

    VedioAuth selectByPrimaryKey(Long id);

    /**
     * 查询相关
     */
    int queryForCount(VedioAuthQueryObject qo);
    
    List<VedioAuth> query(VedioAuthQueryObject qo);
}
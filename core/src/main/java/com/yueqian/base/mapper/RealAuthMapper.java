package com.yueqian.base.mapper;

import com.yueqian.base.domain.RealAuth;
import com.yueqian.base.query.RealAuthQueryObject;

import java.util.List;

public interface RealAuthMapper {

    int insert(RealAuth record);

    RealAuth selectByPrimaryKey(Long id);

    List<RealAuth> selectAll();

    int updateByPrimaryKey(RealAuth record);
    
    /**
     * 后台审核分页查询
     */
    int queryForCount(RealAuthQueryObject qo);
    List<RealAuth> query(RealAuthQueryObject qo);
}

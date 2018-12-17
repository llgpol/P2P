package com.yueqian.business.mapper;

import com.yueqian.business.domain.PlatformBankInfo;
import com.yueqian.business.query.PlatformBankInfoQueryObject;

import java.util.List;

public interface PlatformBankInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PlatformBankInfo record);

    PlatformBankInfo selectByPrimaryKey(Long id);

    List<PlatformBankInfo> selectAll();

    int updateByPrimaryKey(PlatformBankInfo record);
    
    int queryForCount(PlatformBankInfoQueryObject qo);
    List<PlatformBankInfo> query(PlatformBankInfoQueryObject qo);
    
}
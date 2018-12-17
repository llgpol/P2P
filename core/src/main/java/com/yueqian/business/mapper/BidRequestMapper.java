package com.yueqian.business.mapper;

import com.yueqian.business.domain.BidRequest;
import com.yueqian.business.query.BidRequestQueryObject;

import java.util.List;

public interface BidRequestMapper {

    int insert(BidRequest record);

    BidRequest selectByPrimaryKey(Long id);

    int updateByPrimaryKey(BidRequest record);
    
    int queryForCount(BidRequestQueryObject qo);
    List<BidRequest> query(BidRequestQueryObject qo);
}
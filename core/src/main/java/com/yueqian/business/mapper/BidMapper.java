package com.yueqian.business.mapper;

import com.yueqian.business.domain.Bid;
import java.util.List;

public interface BidMapper {

    int insert(Bid record);

    Bid selectByPrimaryKey(Long id);

}
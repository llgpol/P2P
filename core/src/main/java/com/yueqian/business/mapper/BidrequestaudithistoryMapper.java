package com.yueqian.business.mapper;

import java.util.List;

import com.yueqian.business.domain.BidRequestAuditHistory;
import com.yueqian.business.query.BidRequestQueryObject;

public interface BidrequestaudithistoryMapper {

    int insert(BidRequestAuditHistory record);

    BidRequestAuditHistory selectByPrimaryKey(Long id);

    /**
     * 根据一个标查询该标的审核历史
     * @param id
     * @return
     */
    List<BidRequestAuditHistory> listByBidRequest(Long id);
}
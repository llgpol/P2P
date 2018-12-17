package com.yueqian.business.service;

import java.util.List;

import com.yueqian.base.query.PageResult;
import com.yueqian.business.domain.BidRequest;
import com.yueqian.business.domain.BidRequestAuditHistory;
import com.yueqian.business.query.BidRequestQueryObject;

/**
 * 借款相关
 * @author Administrator
 *
 */
public interface IBidRequestService {
	
	void update(BidRequest bidRequest);

	/**
	 * 判断客户是否具有可贷款的权利
	 * @return
	 */
	boolean canApplyBidRequest(Long logininfoId);

	/**
	 * 申请借款
	 * @param bidRequest
	 */
	void apply(BidRequest bidRequest);
	
	/**
	 * 借款分页
	 * @param qo
	 * @return
	 */
	PageResult query(BidRequestQueryObject qo);

	/**
	 * 发标前审核
	 * @param id
	 * @param remark
	 * @param state
	 */
	void publihAudit(Long id, String remark, int state);
	
	/**
	 * 根据一个标查询该标的审核历史
	 * @param id
	 * @return
	 */
	List<BidRequestAuditHistory> listAuditHistoryByBidRequest(Long id);

	BidRequest get(Long id);
	
	
	List<BidRequest> listIndex(int size);
}

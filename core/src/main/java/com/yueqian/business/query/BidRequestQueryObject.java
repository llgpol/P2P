package com.yueqian.business.query;

import com.yueqian.base.query.QueryObject;

/**
 * 借款查询
 * @author Administrator
 *
 */
public class BidRequestQueryObject extends QueryObject{

	private Integer bidRequestState = -1;

	private int[] bidRequestStates; //要查询的多个借款状态
	
	private String orderBy; //按照哪个列排列
	
	private String orderType; //按照什么顺序排列
 	
	public Integer getBidRequestState() {
		return bidRequestState;
	}

	public void setBidRequestState(Integer bidRequestState) {
		this.bidRequestState = bidRequestState;
	}

	public int[] getBidRequestStates() {
		return bidRequestStates;
	}

	public void setBidRequestStates(int[] bidRequestStates) {
		this.bidRequestStates = bidRequestStates;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	
}

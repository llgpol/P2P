package com.yueqian.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.yueqian.base.domain.BaseDomain;
import com.yueqian.base.domain.Logininfo;


/**
 * 投标相关
 *
 */
public class Bid extends BaseDomain{

	private BigDecimal actualRate;// 年化利率(等同于bidrequest上的currentRate)
	private BigDecimal availableAmount;// 这次投标金额
	private Long bidRequestId;// 关联借款
	private String bidRequestTitle;// 冗余数据,等同于借款标题
	private Logininfo bidUser;// 投标人
	private Date bidTime;// 投标时间
	private int bidRequestState;//  标的状态
	
	public BigDecimal getActualRate() {
		return actualRate;
	}
	public void setActualRate(BigDecimal actualRate) {
		this.actualRate = actualRate;
	}
	public BigDecimal getAvailableAmount() {
		return availableAmount;
	}
	public void setAvailableAmount(BigDecimal availableAmount) {
		this.availableAmount = availableAmount;
	}
	public Long getBidRequestId() {
		return bidRequestId;
	}
	public void setBidRequestId(Long bidRequestId) {
		this.bidRequestId = bidRequestId;
	}
	public String getBidRequestTitle() {
		return bidRequestTitle;
	}
	public void setBidRequestTitle(String bidRequestTitle) {
		this.bidRequestTitle = bidRequestTitle;
	}
	public Logininfo getBidUser() {
		return bidUser;
	}
	public void setBidUser(Logininfo bidUser) {
		this.bidUser = bidUser;
	}
	public Date getBidTime() {
		return bidTime;
	}
	public void setBidTime(Date bidTime) {
		this.bidTime = bidTime;
	}
	public int getBidRequestState() {
		return bidRequestState;
	}
	public void setBidRequestState(int bidRequestState) {
		this.bidRequestState = bidRequestState;
	}
	
	
}

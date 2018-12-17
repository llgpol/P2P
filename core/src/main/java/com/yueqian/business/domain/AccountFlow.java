package com.yueqian.business.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.yueqian.base.domain.BaseDomain;


/**
 * 账户流水
 *
 */

public class AccountFlow extends BaseDomain {

	private Long accountId ;  //对应的账户id
	private BigDecimal amount ; //金额 
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date vdate ; // 业务时间
	private int accountActionType ; //对应的 资金变化的类型
	private String note ;  
	private BigDecimal useableAmount ;  //流水之后账户的 可用金额
	private BigDecimal freezedAmount ; //流水之后账户的  冻结金额
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getVdate() {
		return vdate;
	}
	public void setVdate(Date vdate) {
		this.vdate = vdate;
	}
	public int getAccountActionType() {
		return accountActionType;
	}
	public void setAccountActionType(int accountActionType) {
		this.accountActionType = accountActionType;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public BigDecimal getUseableAmount() {
		return useableAmount;
	}
	public void setUseableAmount(BigDecimal useableAmount) {
		this.useableAmount = useableAmount;
	}
	public BigDecimal getFreezedAmount() {
		return freezedAmount;
	}
	public void setFreezedAmount(BigDecimal freezedAmount) {
		this.freezedAmount = freezedAmount;
	}
	
}

package com.yueqian.base.domain;

import java.math.BigDecimal;

import com.yueqian.base.util.BigConst;

public class Account extends BaseDomain {

	private int version;
	private String tradePassword;
	private BigDecimal usableAmount = BigConst.ZERO;
	private BigDecimal freezedAmount = BigConst.ZERO;  
	private BigDecimal unReceiveInterest = BigConst.ZERO;
	private BigDecimal unReceivePrincipal = BigConst.ZERO;
	private BigDecimal unReturnAmount = BigConst.ZERO;
	private BigDecimal remainBorrowLimit = BigConst.INIT_BORROW_LIMIT;
	private BigDecimal borrowLimit = BigConst.INIT_BORROW_LIMIT;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public BigDecimal getUsableAmount() {
		return usableAmount;
	}

	public void setUsableAmount(BigDecimal usableAmount) {
		this.usableAmount = usableAmount;
	}

	public BigDecimal getFreezedAmount() {
		return freezedAmount;
	}

	public void setFreezedAmount(BigDecimal freezedAmount) {
		this.freezedAmount = freezedAmount;
	}

	public BigDecimal getUnReceiveInterest() {
		return unReceiveInterest;
	}

	public void setUnReceiveInterest(BigDecimal unReceiveInterest) {
		this.unReceiveInterest = unReceiveInterest;
	}

	public BigDecimal getUnReceivePrincipal() {
		return unReceivePrincipal;
	}

	public void setUnReceivePrincipal(BigDecimal unReceivePrincipal) {
		this.unReceivePrincipal = unReceivePrincipal;
	}

	public BigDecimal getUnReturnAmount() {
		return unReturnAmount;
	}

	public void setUnReturnAmount(BigDecimal unReturnAmount) {
		this.unReturnAmount = unReturnAmount;
	}

	public BigDecimal getRemainBorrowLimit() {
		return remainBorrowLimit;
	}

	public void setRemainBorrowLimit(BigDecimal remainBorrowLimit) {
		this.remainBorrowLimit = remainBorrowLimit;
	}

	public BigDecimal getBorrowLimit() {
		return borrowLimit;
	}

	public void setBorrowLimit(BigDecimal borrowLimit) {
		this.borrowLimit = borrowLimit;
	}

	public BigDecimal getTotalAmount() {
		return usableAmount.add(freezedAmount).add(unReceivePrincipal);
	}
}

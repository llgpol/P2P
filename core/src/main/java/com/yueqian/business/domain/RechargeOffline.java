package com.yueqian.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.JSONObject;
import com.yueqian.base.domain.BaseAuditDomain;

/**
 * 线下充值单
 *
 */

public class RechargeOffline extends BaseAuditDomain{

	private PlatformBankInfo bankInfo ;   //银行信息对象
	private String 	tradeCode ; //交易号
	private Date tradeTime ; //交易时间
	private BigDecimal amount ;//  交易金额
	private String note ; //交易说明
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	
	public String getJsonString(){
		Map<String, Object> json  = new HashMap<>();
		json.put("id", id);
		json.put("username", this.getApplier().getUsername());
		json.put("tradeCode", tradeCode);
		json.put("tradeTime", tradeTime);
		json.put("amount", amount);
		json.put("note", note);
		
		return JSONObject.toJSONString(json);
	}

	public PlatformBankInfo getBankInfo() {
		return bankInfo;
	}

	public void setBankInfo(PlatformBankInfo bankInfo) {
		this.bankInfo = bankInfo;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getTradeTime() {
		return tradeTime;
	}
	
	
	
}

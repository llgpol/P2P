package com.yueqian.business.domain;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yueqian.base.domain.BaseAuditDomain;



/**
 * 提现对象
 */

public class MoneyWithdraw extends BaseAuditDomain {

	private BigDecimal amount ;
	private BigDecimal chargeFee ;
	private String bankName ; //银行名称
	private String accountName ; //开户人姓名
	private String accountNumber  ; //开户人银行账号
	private String forkName ; //开户支行
	
	public String getJsonString (){
		Map<String , Object> json =  new HashMap<>();
		json.put("id", id) ;
		json.put("username", this.applier.getUsername()) ;
		json.put("realName", accountName) ;
		json.put("applyTime", applyTime) ;
		json.put("bankName", bankName) ;
		json.put("accountNumber", accountNumber) ;
		json.put("forkName", forkName) ;
		json.put("moneyAmount", amount) ;
		return JSONObject.toJSONString(json);
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getChargeFee() {
		return chargeFee;
	}

	public void setChargeFee(BigDecimal chargeFee) {
		this.chargeFee = chargeFee;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getForkName() {
		return forkName;
	}

	public void setForkName(String forkName) {
		this.forkName = forkName;
	}
	
	
	
}

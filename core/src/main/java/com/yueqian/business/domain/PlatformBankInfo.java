package com.yueqian.business.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yueqian.base.domain.BaseDomain;



/**
 * 平台的账户信息
 *
 */

public class PlatformBankInfo extends BaseDomain {

	
	private String bankName ; //银行名称
	private String accountName ; //开户人姓名
	private String accountNumber  ; //开户人银行账号
	private String bankForkName ; //开户支行

	public String getJsonString(){
		Map<String, Object> json = new HashMap<>();
		json.put("id",id);
		json.put("bankName",bankName);
		json.put("accountName",accountName);
		json.put("accountNumber",accountNumber);
		json.put("bankForkName",bankForkName);
		return JSONObject.toJSONString(json);
		
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

	public String getBankForkName() {
		return bankForkName;
	}

	public void setBankForkName(String bankForkName) {
		this.bankForkName = bankForkName;
	}



}

package com.yueqian.business.domain;



import org.springframework.util.StringUtils;

import com.yueqian.base.domain.BaseDomain;
import com.yueqian.base.domain.Logininfo;




public class UserBankinfo extends BaseDomain {

	private  Logininfo logininfo ;
	private String bankName ; //银行名称
	private String accountName ; //开户人姓名
	private String accountNumber  ; //开户人银行账号
	private String forkName ; //开户支行
	
	
	/**
	 * 获取用户真实名字的隐藏字符串，只显示姓氏
	 * 
	 * @param realName
	 *            真实名字
	 * @return
	 */
	public String getAnonymousRealName() {
		if (StringUtils.hasLength(this.accountName)) {
			int len = accountName.length();
			String replace = "";
			replace += accountName.charAt(0);
			for (int i = 1; i < len; i++) {
				replace += "*";
			}
			return replace;
		}
		return accountName;
	}
	
	/**
	 * 获取用户身份号码的隐藏字符串
	 * 
	 * @param idNumber
	 * @return
	 */
	public String getAnonymousAccountNumber() {
		if (StringUtils.hasLength(accountNumber)) {
			int len = accountNumber.length();
			String replace = "";
			for (int i = 0; i < len; i++) {
				if ((i > 5 && i < 10) || (i > len - 5)) {
					replace += "*";
				} else {
					replace += accountNumber.charAt(i);
				}
			}
			return replace;
		}
		return accountNumber;
	}

	public Logininfo getLogininfo() {
		return logininfo;
	}

	public void setLogininfo(Logininfo logininfo) {
		this.logininfo = logininfo;
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

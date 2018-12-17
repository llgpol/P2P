package com.yueqian.base.domain;

import com.yueqian.base.util.BitStatesUtils;

public class Userinfo extends BaseDomain {

	private int version;
	private long bitState = 0;
	private String realName;
	private String idNumber;
	private String phoneNumber;
	private String email;
	private int score; // 用户的风控总分数
	private Long realAuthId; // 用户实名认证 判断是否有对应的实名认证对象 冗余数据
	private SystemDictionaryItem incomeGrade;
	private SystemDictionaryItem Marriage;
	private SystemDictionaryItem kidCount;
	private SystemDictionaryItem educationBackground;
	private SystemDictionaryItem houseCondition;

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public long getBitState() {
		return bitState;
	}

	public void setBitState(long bitState) {
		this.bitState = bitState;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public SystemDictionaryItem getIncomeGrade() {
		return incomeGrade;
	}

	public void setIncomeGrade(SystemDictionaryItem incomeGrade) {
		this.incomeGrade = incomeGrade;
	}

	public SystemDictionaryItem getMarriage() {
		return Marriage;
	}

	public void setMarriage(SystemDictionaryItem marriage) {
		Marriage = marriage;
	}

	public SystemDictionaryItem getKidCount() {
		return kidCount;
	}

	public void setKidCount(SystemDictionaryItem kidCount) {
		this.kidCount = kidCount;
	}

	public SystemDictionaryItem getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(SystemDictionaryItem educationBackground) {
		this.educationBackground = educationBackground;
	}

	public SystemDictionaryItem getHouseCondition() {
		return houseCondition;
	}

	public void setHouseCondition(SystemDictionaryItem houseCondition) {
		this.houseCondition = houseCondition;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Long getRealAuthId() {
		return realAuthId;
	}

	public void setRealAuthId(Long realAuthId) {
		this.realAuthId = realAuthId;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// 判断是否已经绑定了手机
	public boolean getIsBindPhone() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_PHONE);
	}

	// 判断是否已经绑定看了银行卡
	public boolean getIsBindBank() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_HAS_BIND_BANK);
	}

	// 判断是否已经绑定了邮箱
	public boolean getIsBindEmail() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_EMAIL);
	}

	// 添加绑定的状态码
	public void addState(Long state) {
		bitState = BitStatesUtils.addState(this.bitState, state);
	}

	// 移除状态码
	public void removeState(Long state) {
		bitState = BitStatesUtils.removeState(this.bitState, state);
	}

	// 判断用户是否已经填写了基本资料
	public boolean getIsBasicInfo() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BASIC_INFO);
	}

	// 判断用户是否已经实名认证
	public boolean getIsRealAuth() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_REAL_AUTH);
	}

	// 判断用户是否已经视频认证
	public boolean getIsVedioAuth() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_VEDIO_AUTH);
	}

	// 判断用户是否已经有一个借款在审核流程中
	public boolean getHasBidRequestInProcess() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS);
	}

	// 判断用户是否已经有一个提现在审核流程中
	public boolean getHasWithdrawInProcess() {
		return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_HAS_WITHDRAW_PROCESS);
	}

	public void addstate(long state) {
		this.setBitState(BitStatesUtils.addState(this.bitState, state));
	}
}

package com.yueqian.base.service;

public interface IVerifyCodeService {
	
	/**
	 * 用于发送手机验证码
	 * @param phoneNumber
	 */
	void sendVerifyCode(String phoneNumber);

	/**
	 * 用于验证手机验证码
	 * @param phoneNumber
	 * @param verifyCode
	 * @return
	 */
	boolean verify(String phoneNumber, String verifyCode);
}

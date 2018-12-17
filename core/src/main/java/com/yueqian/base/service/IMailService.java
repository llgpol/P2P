package com.yueqian.base.service;

/**
 * 用于发送邮件
 * @author Administrator
 *
 */
public interface IMailService {
	
	/**
	 * 发送邮件
	 * @param target 目标地址
	 * @param title
	 * @param content
	 */
	void sendMail(String target , String title , String content);
	
}

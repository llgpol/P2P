package com.yueqian.base.service;

import java.util.List;
import java.util.Map;

import com.yueqian.base.domain.Logininfo;
import com.yueqian.base.domain.Userinfo;

/**
 * 用户信息
 * @author Administrator
 *
 */
public interface IUserinfoService {
	
	/**
	 * 乐观锁支持
	 * @param userinfo
	 */
	void update(Userinfo userinfo);

	void add(Userinfo userinfo);

	Userinfo get(long id);

	/**
	 * 绑定手机号
	 * @param phoneNumber
	 * @param verifyCode
	 */
	void bindPhone(String phoneNumber, String verifyCode);
	
	/**
	 * 发送邮件
	 * @param email
	 */
	void sendEmail(String email);
	
	/**
	 * 得到userinfo
	 */
	Userinfo getUserinfo();

	/**
	 * 绑定邮箱
	 * @param uuid
	 */
	void bindEmail(String uuid);

	/**
	 * 更新用户基本数据
	 * @param userinfo
	 */
	void updateBasicInfo(Userinfo userinfo);
	
	/**
	 * 用于用户的自动补全
	 * @param keyword
	 * @return
	 * 		返回的map：{id:logininfoId , username:username}
	 */
	List<Map<String, Object>> autoComplate(String keyword);
}

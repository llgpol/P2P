package com.yueqian.base.service;

import com.yueqian.base.domain.Logininfo;

public interface ILogininfoService {

	boolean checkUserName(String username);

	void register(String username, String password);

	Logininfo login(String username, String password,String ip,int userType);

	/**
	 * 初始化超级管理员
	 */
	void initAdmin();

}

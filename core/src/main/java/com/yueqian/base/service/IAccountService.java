package com.yueqian.base.service;

import com.yueqian.base.domain.Account;

/**
 * 账户相关信息
 * @author Administrator
 *
 */
public interface IAccountService {

	/**
	 * 乐观锁支持
	 */
	void update(Account account);

	void add(Account account);

	Account get(long id);

	/**
	 * 得到当前用户
	 * @return
	 */
	Account getCurrent();


	
	
	
}

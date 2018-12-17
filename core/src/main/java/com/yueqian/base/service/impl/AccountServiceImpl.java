package com.yueqian.base.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqian.base.domain.Account;
import com.yueqian.base.mapper.AccountMapper;
import com.yueqian.base.service.IAccountService;
import com.yueqian.base.util.UserContext;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	private AccountMapper accountMapper;
	
	@Override
	public void update(Account account) {
		int ret = accountMapper.updateByPrimaryKey(account);
		if (ret==0) {
			throw new RuntimeException("乐观锁失败，account："+account.getId());
		}
	}

	@Override
	public void add(Account account) {
		accountMapper.insert(account);
	}

	@Override
	public Account get(long id) {
		return accountMapper.selectByPrimaryKey(id);
	}

	@Override
	public Account getCurrent() {
		return this.get(UserContext.getLogininfo().getId());
	}

}

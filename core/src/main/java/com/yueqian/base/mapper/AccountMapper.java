package com.yueqian.base.mapper;

import com.yueqian.base.domain.Account;

public interface AccountMapper {

    int insert(Account record);

    Account selectByPrimaryKey(Long id);

    int updateByPrimaryKey(Account record);
}
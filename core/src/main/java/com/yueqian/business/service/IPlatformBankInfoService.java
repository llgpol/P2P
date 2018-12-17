package com.yueqian.business.service;

import java.util.List;

import com.yueqian.base.query.PageResult;
import com.yueqian.business.domain.PlatformBankInfo;
import com.yueqian.business.query.PlatformBankInfoQueryObject;

public interface IPlatformBankInfoService {
	PageResult query(PlatformBankInfoQueryObject qo);

	void updateOrSave(PlatformBankInfo bankInfo);

	List<PlatformBankInfo> listAll();
}

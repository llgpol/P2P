package com.yueqian.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqian.base.query.PageResult;
import com.yueqian.business.domain.PlatformBankInfo;
import com.yueqian.business.mapper.PlatformBankInfoMapper;
import com.yueqian.business.query.PlatformBankInfoQueryObject;
import com.yueqian.business.service.IPlatformBankInfoService;

@Service
public class PlatformBankInfoServiceImpl implements IPlatformBankInfoService{

	@Autowired
	private PlatformBankInfoMapper platformBankInfoMapper;
	
	@Override
	public PageResult query(PlatformBankInfoQueryObject qo) {
		int count = platformBankInfoMapper.queryForCount(qo);
		if (count > 0) {
			List<PlatformBankInfo> list = platformBankInfoMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void updateOrSave(PlatformBankInfo bankInfo) {
		if (bankInfo.getId() != null) {
			platformBankInfoMapper.updateByPrimaryKey(bankInfo);
		}else {
			platformBankInfoMapper.insert(bankInfo);
		}
		
	}

	@Override
	public List<PlatformBankInfo> listAll() {
		return platformBankInfoMapper.selectAll();
	}

}

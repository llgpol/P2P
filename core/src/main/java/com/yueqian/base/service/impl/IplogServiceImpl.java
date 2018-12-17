package com.yueqian.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqian.base.domain.Iplog;
import com.yueqian.base.mapper.IplogMapper;
import com.yueqian.base.query.IplogQueryObject;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.service.IIplogService;

@Service
public class IplogServiceImpl implements IIplogService{

	@Autowired
	private IplogMapper iplogMapper;
	@Override
	public PageResult query(IplogQueryObject qo) {
		int count = iplogMapper.queryForCount(qo);
		if (count>0) {
			List<Iplog> list = iplogMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

}

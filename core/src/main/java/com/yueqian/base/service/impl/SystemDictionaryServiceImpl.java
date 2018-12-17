package com.yueqian.base.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqian.base.domain.SystemDictionary;
import com.yueqian.base.domain.SystemDictionaryItem;
import com.yueqian.base.mapper.SystemDictionaryItemMapper;
import com.yueqian.base.mapper.SystemDictionaryMapper;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.query.SystemDictionaryQueryObject;
import com.yueqian.base.service.ISystemDictionaryService;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService{

	@Autowired
	private SystemDictionaryMapper systemDictionaryMapper;
	
	@Autowired
	private SystemDictionaryItemMapper systemDictionaryItemMapper;
	
	@Override
	public PageResult queryDics(SystemDictionaryQueryObject qo) {
		int count = systemDictionaryMapper.queryForCount(qo);
		if (count>0) {
			List<SystemDictionary> list = systemDictionaryMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void updateOrSaveDics(SystemDictionary systemDictionary) {
		if (systemDictionary.getId() != null) {
			systemDictionaryMapper.updateByPrimaryKey(systemDictionary);
		}else {
			systemDictionaryMapper.insert(systemDictionary);
		}
		
	}

	@Override
	public PageResult queryItem(SystemDictionaryQueryObject qo) {
		int count = systemDictionaryItemMapper.queryForCount(qo);
		if (count>0) {
			List<SystemDictionaryItem> list = systemDictionaryItemMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public List<SystemDictionary> list() {
		return systemDictionaryMapper.selectAll();
	}

	@Override
	public void updateOrSaveItem(SystemDictionaryItem item) {
		if (item.getId()!=null) {
			systemDictionaryItemMapper.updateByPrimaryKey(item);
		}else {
			systemDictionaryItemMapper.insert(item);
		}
		
	}

	@Override
	public List<SystemDictionaryItem> listByParentSn(String sn) {
		return systemDictionaryItemMapper.listByParentSn(sn);
	}

}

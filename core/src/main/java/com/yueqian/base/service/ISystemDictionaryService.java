package com.yueqian.base.service;

import java.util.List;

import com.yueqian.base.domain.SystemDictionary;
import com.yueqian.base.domain.SystemDictionaryItem;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.query.SystemDictionaryQueryObject;

/**
 * 数据字典相关服务
 * @author Administrator
 *
 */
public interface ISystemDictionaryService {

	/**
	 * 数据字典分类分页查询
	 * @return
	 */
	PageResult queryDics(SystemDictionaryQueryObject qo);

	/**
	 * 修改或者保存数据字典
	 * @param systemDictionary
	 */
	void updateOrSaveDics(SystemDictionary systemDictionary);
	
	/**
	 * 数据字典明细分页查询
	 * @param qo
	 * @return
	 */
	PageResult queryItem(SystemDictionaryQueryObject qo);

	/**
	 * 查询所有数据字典明细
	 * @return
	 */
	List<SystemDictionary> list();

	/**
	 * 修改或者保存数据字典明细
	 * @param systemDictionary
	 */
	void updateOrSaveItem(SystemDictionaryItem item);

	/**
	 * 根据数据字典分类sn查询明细
	 * @param sn
	 * @return
	 */
	List<SystemDictionaryItem> listByParentSn(String sn);
}

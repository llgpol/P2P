package com.yueqian.base.mapper;

import com.yueqian.base.domain.SystemDictionaryItem;
import com.yueqian.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {
    

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SystemDictionaryItem record);
    
    /**
     * 分页相关查询
     */
    int queryForCount(SystemDictionaryQueryObject qo);
    List<SystemDictionaryItem> query(SystemDictionaryQueryObject qo);

    /**
	 * 根据数据字典分类sn查询明细
	 * @param sn
	 * @return
	 */
	List<SystemDictionaryItem> listByParentSn(String sn);
    
}
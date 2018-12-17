package com.yueqian.base.mapper;

import com.yueqian.base.domain.SystemDictionary;
import com.yueqian.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryMapper {
    

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();
    
    /**
     * 分页方法
     */
    int queryForCount(SystemDictionaryQueryObject qo);
    List<SystemDictionary> query(SystemDictionaryQueryObject qo);
    
    int updateByPrimaryKey(SystemDictionary record);
}
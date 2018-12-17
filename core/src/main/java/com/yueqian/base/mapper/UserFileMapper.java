package com.yueqian.base.mapper;

import com.yueqian.base.domain.UserFile;
import com.yueqian.base.query.UserFileQueryObject;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UserFileMapper {

	int insert(UserFile record);

	UserFile selectByPrimaryKey(Long id);

	List<UserFile> selectAll();

	int updateByPrimaryKey(UserFile record);

	/**
	 * 列出用户风控资源对象
	 * 
	 * @param logininfoId
	 * @return
	 */
	List<UserFile> listFilesByHasType(@Param("logininfoId") Long logininfoId, @Param("hasType") boolean hasType);

	/**
	 * 风控资料查询
	 */
	int queryForCount(UserFileQueryObject qo);
	List<UserFile> query(UserFileQueryObject qo);
	
	
	
	
	
}
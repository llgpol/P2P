package com.yueqian.base.service;

import java.util.List;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import com.yueqian.base.domain.UserFile;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.query.UserFileQueryObject;

/**
 * 风控资料服务
 * @author Administrator
 *
 */
public interface IUserFileService {

	/**
	 * 上传一个风控资料
	 * @param fileName
	 */
	void apply(String fileName);

	/**
	 * 列出用户风控资源对象
	 * @param id
	 * @return
	 */
	List<UserFile> listFilesByHasType(Long logininfoId,boolean hasType);

	/**
	 *  批量的处理用户风控资料类型的选择
	 * @param id
	 * @param fileType
	 */
	void batchUpdateFileType(Long[] ids, Long[] fileTypes);

	/**
	 * 分页查询
	 */
	PageResult query(UserFileQueryObject qo);

	void audit(Long id, int score, String remark, int state);
	
	List<UserFile> queryForList(UserFileQueryObject qo); 
}

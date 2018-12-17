package com.yueqian.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqian.base.domain.SystemDictionaryItem;
import com.yueqian.base.domain.UserFile;
import com.yueqian.base.domain.Userinfo;
import com.yueqian.base.mapper.UserFileMapper;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.query.UserFileQueryObject;
import com.yueqian.base.service.IUserFileService;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.util.UserContext;

@Service
public class UserFileServiceImpl implements IUserFileService{

	@Autowired
	private UserFileMapper userfileMapper;

	@Autowired
	private IUserinfoService userinfoService;
	
	@Override
	public void apply(String fileName) {
		UserFile userFile = new UserFile();
		userFile.setApplier(UserContext.getLogininfo());
		userFile.setApplyTime(new Date());
		userFile.setImage(fileName);
		userFile.setState(userFile.STATE_NORMAL);
		
		userfileMapper.insert(userFile);
	}

	@Override
	public List<UserFile> listFilesByHasType(Long logininfoId,boolean hasType) {
		return userfileMapper.listFilesByHasType(logininfoId,hasType);
	}

	@Override
	public void batchUpdateFileType(Long[] ids, Long[] fileTypes) {
		for(int i = 0;i < ids.length; i++ ) {
			UserFile userFile = userfileMapper.selectByPrimaryKey(ids[i]);
			SystemDictionaryItem systemDictionaryItem = new SystemDictionaryItem();
			systemDictionaryItem.setId(fileTypes[i]);
			userFile.setFileType(systemDictionaryItem);
			userfileMapper.updateByPrimaryKey(userFile);
		}
		
	}

	@Override
	public PageResult query(UserFileQueryObject qo) {
		int count = userfileMapper.queryForCount(qo);
		if (count>0) {
			List<UserFile> list = userfileMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize()); 
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void audit(Long id, int score, String remark, int state) {
		//找到userFile状态
		UserFile userFile = userfileMapper.selectByPrimaryKey(id);
		if (userFile != null && userFile.getState()==userFile.STATE_NORMAL) {
			//设置通用属性
			userFile.setAuditor(UserContext.getLogininfo());
			userFile.setAuditTime(new Date());
			userFile.setState(state);
			//如果审核通过添加相关属性
			if (state == userFile.STATE_AUDIT) {
				userFile.setScore(score);
				Long applierId = userFile.getApplier().getId();
				Userinfo userinfo = userinfoService.get(applierId);
				userinfo.setScore(userinfo.getScore()+score);
				userinfoService.update(userinfo);
			}
			userfileMapper.updateByPrimaryKey(userFile);
		}	
	}

	@Override
	public List<UserFile> queryForList(UserFileQueryObject qo) {
		return userfileMapper.query(qo);
	}
	
	
	
	
	
	
}

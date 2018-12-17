package com.yueqian.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqian.base.domain.Logininfo;
import com.yueqian.base.domain.Userinfo;
import com.yueqian.base.domain.VedioAuth;
import com.yueqian.base.mapper.VedioAuthMapper;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.query.VedioAuthQueryObject;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.service.IVedioAuthService;
import com.yueqian.base.util.BitStatesUtils;
import com.yueqian.base.util.UserContext;

@Service
public class VedioAuthServiceImpl implements IVedioAuthService{

	@Autowired
	private VedioAuthMapper vedioAuthMapper;
	
	@Autowired
	private IUserinfoService userinfoService;
	
	@Override
	public PageResult query(VedioAuthQueryObject qo) {
		int count = vedioAuthMapper.queryForCount(qo);
		if (count>0) {
			List<VedioAuth> list = vedioAuthMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void audit(Long logininfoValue, String remark, int state) {
		Userinfo userinfo = userinfoService.get(logininfoValue);
		//判断用户没有视频认证
		if ( userinfo != null && !userinfo.getIsVedioAuth()) {
			//添加一个视频认证对象，设置相关属性
			VedioAuth vedioAuth = new VedioAuth();
			Logininfo applier = new Logininfo();
			applier.setId(logininfoValue);
			
			vedioAuth.setApplier(applier);
			vedioAuth.setApplyTime(new Date());
			vedioAuth.setAuditor(UserContext.getLogininfo());
			vedioAuth.setAuditTime(new Date());
			vedioAuth.setRemark(remark);
			vedioAuth.setState(state);
			
			vedioAuthMapper.insert(vedioAuth);
			
			//如果状态审核通过，修改用户状态码
			if (state == VedioAuth.STATE_AUDIT) {
				userinfo.addState(BitStatesUtils.OP_VEDIO_AUTH);
				userinfoService.update(userinfo);
			}
		}
		
		
		
	}

}

package com.yueqian.base.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqian.base.domain.RealAuth;
import com.yueqian.base.domain.Userinfo;
import com.yueqian.base.mapper.RealAuthMapper;
import com.yueqian.base.query.PageResult;
import com.yueqian.base.query.RealAuthQueryObject;
import com.yueqian.base.service.IRealAuthService;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.util.BigConst;
import com.yueqian.base.util.BitStatesUtils;
import com.yueqian.base.util.UserContext;

@Service
public class RealAuthServiceImpl implements IRealAuthService {

	@Autowired
	private RealAuthMapper realAuthMapper;
	
	@Autowired
	private IUserinfoService userinfoService;
	
	@Override
	public RealAuth get(Long id) {
		return realAuthMapper.selectByPrimaryKey(id);
	}

	@Override
	public void apply(RealAuth realAuth) {
		Userinfo userinfo = userinfoService.getUserinfo();
		//判断当前用户没有实名认证并且当前用户不处以待审核状态
		if (!userinfo.getIsRealAuth() && userinfo.getRealAuthId()==null) {
			//保存一个实名认证对象
			realAuth.setState(RealAuth.STATE_NORMAL);
			realAuth.setApplier(UserContext.getLogininfo());
			realAuth.setApplyTime(new Date());
			realAuthMapper.insert(realAuth);
			//把实名认证id设置给userinfo
			userinfo.setRealAuthId(realAuth.getId());
			userinfoService.update(userinfo);
		}
		
		
	}

	@Override
	public PageResult query(RealAuthQueryObject qo) {
		int count = realAuthMapper.queryForCount(qo);
		if (count>0) {
			List<RealAuth> list = realAuthMapper.query(qo);
			return new PageResult(list, count, qo.getCurrentPage(), qo.getPageSize());	
		}
		return PageResult.empty(qo.getPageSize());
	}

	@Override
	public void audit(Long id, String remark, int state) {
		// 根据id得到实名验证对象
		RealAuth realAuth = get(id);
		// 如果对象存在，而且对象处于待审核状态
		if (realAuth != null && realAuth.getState() == realAuth.STATE_NORMAL) {
			// 1.设置通用属性
			realAuth.setAuditor(UserContext.getLogininfo());
			realAuth.setAuditTime(new Date());
			realAuth.setState(state);
			Userinfo applier = userinfoService.get(realAuth.getApplier().getId());
			// 2.如果状态是审核通过
			if (state==realAuth.STATE_AUDIT) {
				//1)保证用户处于未审核状态
				if (!applier.getIsRealAuth()) {
					//2)添加审核状态码，设置userinfo上面的剩余数据，重新realauthid
					applier.addstate(BitStatesUtils.OP_REAL_AUTH);
					applier.setRealName(realAuth.getRealName());
					applier.setIdNumber(realAuth.getIdNumber());
					applier.setRealAuthId(realAuth.getId());
				}
			}else {
				// 3.如果状态是审核拒绝
				//		1)userinfo中的realauthid设置为空
				applier.setRealAuthId(null);
			}
			userinfoService.update(applier);
			realAuthMapper.updateByPrimaryKey(realAuth);
			
			
		}
		
		
		
		
		
	}

}

package com.yueqian.base.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yueqian.base.domain.Account;
import com.yueqian.base.domain.Iplog;
import com.yueqian.base.domain.Logininfo;
import com.yueqian.base.domain.Userinfo;
import com.yueqian.base.mapper.IplogMapper;
import com.yueqian.base.mapper.LogininfoMapper;
import com.yueqian.base.service.IAccountService;
import com.yueqian.base.service.ILogininfoService;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.util.BigConst;
import com.yueqian.base.util.MD5;
import com.yueqian.base.util.UserContext;

import net.sf.cglib.transform.impl.AddDelegateTransformer;

@Service
public class LogininfoServiceImpl implements ILogininfoService{

	@Autowired
	private LogininfoMapper logininfoMapper;
	
	@Autowired
	private IUserinfoService userinfoService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private	IplogMapper iplogMapper;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@Override
	public boolean checkUserName(String username) {
		return logininfoMapper.getCountByUsername(username)>0;
	}

	@Override
	public void register(String username, String password) {
		int count = logininfoMapper.getCountByUsername(username);
				
		if (count<=0) {
			Logininfo logininfo = new Logininfo();
			logininfo.setUsername(username);
			logininfo.setPassword(MD5.encode(password));
			logininfo.setState(logininfo.STATE_NORMAL);
			logininfo.setUserType(logininfo.USER_CLIENT);
			logininfoMapper.insert(logininfo);
			
			//初始化用户信息和账号信息
			Account account = new Account();
			account.setId(logininfo.getId());
			accountService.add(account);
			
			Userinfo userinfo = new Userinfo();
			userinfo.setId(logininfo.getId());
			userinfoService.add(userinfo);
			
		}else {
			throw new RuntimeException("该用户名已存在");
		}	
	}

	@Override
	public Logininfo login(String username, String password,String ip,int userType) {
		Logininfo logininfo = logininfoMapper.login(username,MD5.encode(password),userType);
		Iplog iplog = new Iplog();
		iplog.setIp(ip);
		iplog.setLoginTime(new Date());
		iplog.setUserName(username);
		iplog.setUserType(userType);
		if (logininfo!=null) {
			UserContext.putLogininfo(logininfo);
			iplog.setState(iplog.STATE_SUCCESS);
		}else{
			iplog.setState(iplog.STATE_FAILED);
					
		}
		iplogMapper.insert(iplog);
		return logininfo;
	}

	@Override
	public void initAdmin() {
		//查询是否有管理员，如果没有创建一个
		int count = logininfoMapper.getCountByUserType(Logininfo.USER_MANAGER);
		//如果没有创建一个默认管理员
		if (count==0) {
			Logininfo admin = new Logininfo();
			admin.setUsername(BigConst.DEFAULT_ADMIN_USERNAME);
			admin.setPassword(MD5.encode(BigConst.DEFAULT_ADMIN_PASSWORD));
			admin.setUserType(Logininfo.USER_MANAGER);
			admin.setState(Logininfo.STATE_NORMAL);
			logininfoMapper.insert(admin);
		}
	}	
	
}

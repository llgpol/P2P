package com.yueqian.mgr.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


import com.yueqian.base.service.ILogininfoService;


/**
 * 初始化超级管理员的监听器
 * 1.在spring中只要实现ApplicationListener，就可以用来监听spring中的特殊事件
 * 2.在spring中ApplicationEvent相当于所有的事件，如果实现的是ApplicationListener<ApplicationEvent>，
 * 就说明监听所有的事件
 * 3.现在只想监听spring启动完成的事件，只需要监听ContextRefreshedEvent就可以。
 * @author Administrator
 *
 */

@Component
public class InitAdminListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private ILogininfoService loginingoService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		loginingoService.initAdmin();
		
	}

}

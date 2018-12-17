package com.yueqian.base.service.impl;

import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.yueqian.base.service.IMailService;

@Service
public class MailServiceImpl implements IMailService{

	@Value("${mail.host}")
	private String host;
	
	@Value("${mail.username}")
	private String username;
	
	@Value("${mail.password}")
	private String password;
	
	@Value("${mail.fromAddress}")
	private String fromAddress;
	
	@Override
	public void sendMail(String target, String title, String content) {
		try {
			JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
			// 设置SMTP服务器地址
			javaMailSender.setHost(host);
			// 创建一个邮箱对象
			MimeMessage message = javaMailSender.createMimeMessage();
			// 抽奖邮箱助手
			MimeMessageHelper messageHelper = new MimeMessageHelper(message);
			// 通过邮箱助手设置邮箱相关内容
			// 设置目标
			messageHelper.setTo(target);
			// 设置from
			messageHelper.setFrom(new InternetAddress(fromAddress));
			// 设置邮件标题
			messageHelper.setSubject(title);
			// 设置邮箱内容
			messageHelper.setText(content);
			//设置用户名和密码
			javaMailSender.setUsername(username);
			javaMailSender.setPassword(password);
			Properties prop = new Properties();
			//设置为true，让服务器进行验证，验证用户名和密码是否正确
			prop.put("mail.smtp.auth", true);
			prop.put("mail.smtp.timeout", 25000);
			javaMailSender.setJavaMailProperties(prop);
			//发送邮件
			javaMailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("邮件发送失败！");
		}
		
		
		
	}

}

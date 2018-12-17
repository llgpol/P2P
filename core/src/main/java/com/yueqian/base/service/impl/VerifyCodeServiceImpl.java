package com.yueqian.base.service.impl;

import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.yueqian.base.service.IVerifyCodeService;
import com.yueqian.base.util.BigConst;
import com.yueqian.base.util.DateUtil;
import com.yueqian.base.util.UserContext;
import com.yueqian.base.vo.VerifyCodeVO;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService{

	@Value("${sms.username}")
	private String username;
	
	@Value("${sms.password}")
	private String password;
	
	@Value("${sms.apikey}")
	private String apikey;
	
	@Value("${sms.url}")
	private String url;
	
	
	@Override
	public void sendVerifyCode(String phoneNumber) {
		//判断当前能否发送短信
		//从session中获取最后一次发送短信的时间
		VerifyCodeVO vc = UserContext.getVerifyCode();
		if(vc==null ||DateUtil.getBetweenSecond(vc.getLastSendTime(), new Date()) >90) {
			//正常发送短信
			//生成验证码
			String verifyCode = UUID.randomUUID().toString().substring(0, 4);
			//发送短信
			try {
				//创建一个URL对象
				URL url = new URL(this.url);
				//通过url得到一个HTTPURLConnection对象
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				//拼接POST请求内容
				StringBuilder content = new StringBuilder(100)
						.append("username=").append(username)
						.append("&password=").append(password)
						.append("&apikey").append(apikey)
						.append("&mobile=").append(phoneNumber)
						.append("&content=").append("The verification code is ")
						.append(verifyCode).append(",please use it in 5 minutes.");
				//发送POST请求，POST和GET必须是大写
				conn.setRequestMethod("POST");
				//设置POST请求是有请求体的
				conn.setDoOutput(true);
				//写入POST请求体
				conn.getOutputStream().write(content.toString().getBytes());
				//得到响应流（其实已经发送了）
				String response = StreamUtils.copyToString(conn.getInputStream(), Charset.forName("UTF-8"));
				if (response.startsWith("success:")) {
					//发送成功
					//把验证码，手机号，发送时间装配到vo并存储到session中
					vc = new VerifyCodeVO();
					vc.setLastSendTime(new Date());
					vc.setPhoneNumber(phoneNumber);
					vc.setVerifyCode(verifyCode);
					UserContext.putVerifyCode(vc);
				}else {
					//发送失败
					throw new RuntimeException();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("短信发送失败！");
			}
		}else {
			throw new RuntimeException("发送过于频繁！");
		}
		
	}

	@Override
	public boolean verify(String phoneNumber, String verifyCode) {
		VerifyCodeVO vc = UserContext.getVerifyCode();
		if (vc!=null
				&& vc.getPhoneNumber().equals(phoneNumber)
				&& vc.getVerifyCode().equalsIgnoreCase(verifyCode)
				&& DateUtil.getBetweenSecond(vc.getLastSendTime(), new Date())<=BigConst.VERIFYCODE_VEILDATE_SECOND) {
			return true;
		}
		return false;
	}

}

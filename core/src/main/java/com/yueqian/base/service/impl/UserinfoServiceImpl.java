package com.yueqian.base.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.yueqian.base.domain.Logininfo;
import com.yueqian.base.domain.MailVerify;
import com.yueqian.base.domain.Userinfo;
import com.yueqian.base.mapper.MailverifyMapper;
import com.yueqian.base.mapper.UserinfoMapper;
import com.yueqian.base.service.IMailService;
import com.yueqian.base.service.IUserinfoService;
import com.yueqian.base.service.IVerifyCodeService;
import com.yueqian.base.util.BigConst;
import com.yueqian.base.util.BitStatesUtils;
import com.yueqian.base.util.DateUtil;
import com.yueqian.base.util.UserContext;

@Service
public class UserinfoServiceImpl implements IUserinfoService {

	@Value("${mail.applicationUrl}")
	private String hostUrl;

	@Autowired
	private UserinfoMapper userinfoMapper;

	@Autowired
	private IVerifyCodeService verifyCodeService;

	@Autowired
	private MailverifyMapper mailverifyMapper;

	@Autowired
	private IMailService mailService;
	
	@Override
	public void update(Userinfo userinfo) {
		int ret = userinfoMapper.updateByPrimaryKey(userinfo);
		if (ret == 0) {
			throw new RuntimeException("乐观锁失败，userinfo：" + userinfo.getId());
		}

	}

	@Override
	public void add(Userinfo userinfo) {
		userinfoMapper.insert(userinfo);

	}

	@Override
	public Userinfo get(long id) {

		return userinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void bindPhone(String phoneNumber, String verifyCode) {
		// 是否已经绑定手机号
		Userinfo userinfo = this.getUserinfo();
		if (!userinfo.getIsBindPhone()) {
			// 验证验证码合法
			boolean ret = verifyCodeService.verify(phoneNumber, verifyCode);
			if (ret) {
				// 如果合法，给用户绑定手机
				userinfo.addstate(BitStatesUtils.OP_BIND_PHONE);
				userinfo.setPhoneNumber(phoneNumber);
				this.update(userinfo);
			} else {
				// 抛出异常
				throw new RuntimeException("绑定手机失败");
			}
		}
	}

	@Override
	public void sendEmail(String email) {
		Userinfo userinfo = this.getUserinfo();
		// 如果当前用户没有绑定邮箱
		if (!userinfo.getIsBindEmail()) {
			String uuid = UUID.randomUUID().toString();
			// 构建一份要发送的邮件
			StringBuilder content = new StringBuilder(100).append("点击<a href='").append(this.hostUrl)
					.append("bindEmail.do?key=").append(uuid).append("'>这里</a>完成邮箱绑定，有效期为")
					.append(BigConst.EMAILVERIFY_VEILDATE_DAY).append("天。");
			try {
				//发送邮件
				mailService.sendMail(email, "邮箱验证", content.toString());
				// 构建一个MailVerify对象
				MailVerify mailVerify = new MailVerify();
				mailVerify.setUuid(uuid);
				mailVerify.setEmail(email);
				mailVerify.setSendDate(new Date());
				mailVerify.setUserinfoId(userinfo.getId());

				mailverifyMapper.insert(mailVerify);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("验证邮件发送失败");
			}

		}

	}

	@Override
	public Userinfo getUserinfo() {
		return this.get(UserContext.getLogininfo().getId());
	}

	@Override
	public void bindEmail(String uuid) {
		// 根据uuid得到MailVerify对象
		MailVerify mv = mailverifyMapper.selectByUUID(uuid);
		if (mv != null) {
			Userinfo userinfo = this.get(mv.getUserinfoId());
			// 判断是否绑定了邮箱
			if (!userinfo.getIsBindEmail()) {
				// 判断有效期
				if (mv != null && DateUtil.getBetweenSecond(mv.getSendDate(),
						new Date()) <= BigConst.EMAILVERIFY_VEILDATE_DAY * 24 * 3600) {
					userinfo.addState(BitStatesUtils.OP_BIND_EMAIL);
					userinfo.setEmail(mv.getEmail());
					this.update(userinfo);
					return ;
				}
			}	
		}
		throw new RuntimeException("邮箱绑定失败！");
	}

	@Override
	public void updateBasicInfo(Userinfo userinfo) {
		Userinfo ui = this.getUserinfo();
		ui.setEducationBackground(userinfo.getEducationBackground());
		ui.setHouseCondition(userinfo.getHouseCondition());
		ui.setIncomeGrade(userinfo.getIncomeGrade());
		ui.setMarriage(userinfo.getMarriage());
		ui.setKidCount(userinfo.getKidCount());
		if (!ui.getIsBasicInfo()) {
			ui.addState(BitStatesUtils.OP_BASIC_INFO);
		}
		this.update(ui);
	}

	@Override
	public List<Map<String, Object>> autoComplate(String keyword) {
		System.out.println(keyword);
		return userinfoMapper.autoComplate(keyword); 
	}
}

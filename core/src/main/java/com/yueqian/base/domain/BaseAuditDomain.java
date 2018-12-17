package com.yueqian.base.domain;

import java.util.Date;

/**
 * 基础的审核对象
 * @author Administrator
 *
 */
abstract public class BaseAuditDomain extends BaseDomain {

	public static final int STATE_NORMAL = 0;// 正常
	public static final int STATE_AUDIT = 1;// 审核通过
	public static final int STATE_REJECT = 2;// 审核拒接

	protected int state;// 状态
	protected Logininfo auditor;// 申请人
	protected Logininfo applier;// 申请人
	protected String remark;// 备注
	protected Date applyTime;// 申请时间
	protected Date auditTime;// 审核时间

	public Logininfo getApplier() {
		return applier;
	}

	public void setApplier(Logininfo applier) {
		this.applier = applier;
	}
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Logininfo getAuditor() {
		return auditor;
	}

	public void setAuditor(Logininfo auditor) {
		this.auditor = auditor;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getStateDisplay() {
		switch (state) {
		case STATE_NORMAL:
			return "待审核";
		case STATE_AUDIT:
			return "审核通过";
		case STATE_REJECT:
			return "审核拒绝";
		default:
			return "";
		}
	}
}

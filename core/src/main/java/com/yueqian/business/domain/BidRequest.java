package com.yueqian.business.domain;



import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.yueqian.base.domain.BaseDomain;
import com.yueqian.base.domain.Logininfo;
import com.yueqian.base.util.BigConst;

import static com.yueqian.base.util.BigConst.*;


/**
 * 借款对象
 * 
 * 
 */

public class BidRequest extends BaseDomain {
	private int version;// 版本号
	private int returnType;// 还款类型(等额本息)
	private int bidRequestType;// 借款类型(信用标)
	private int bidRequestState;// 借款状态
	private BigDecimal bidRequestAmount;// 借款总金额
	private BigDecimal currentRate;// 年化利率
	private BigDecimal minBidAmount;// 最小借款金额
	private int monthes2Return;// 还款月数
	private int bidCount = 0;// 已投标次数(冗余)
	private BigDecimal totalRewardAmount;// 总回报金额(总利息)
	private BigDecimal currentSum  = BigConst.ZERO;// 当前已投标总金额
	private String title;// 借款标题
	private String description;// 借款描述
	private String note;// 风控意见
	private Date disableDate;// 招标截止日期
	private int disableDays;// 招标天数
	private Logininfo createUser;// 借款人
	private List<Bid> bids;// 针对该借款的投标
	private Date applyTime;// 申请时间
	private Date publishTime;// 发标时间
	
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public int getReturnType() {
		return returnType;
	}

	public void setReturnType(int returnType) {
		this.returnType = returnType;
	}

	public int getBidRequestType() {
		return bidRequestType;
	}

	public void setBidRequestType(int bidRequestType) {
		this.bidRequestType = bidRequestType;
	}

	public int getBidRequestState() {
		return bidRequestState;
	}

	public void setBidRequestState(int bidRequestState) {
		this.bidRequestState = bidRequestState;
	}

	public BigDecimal getBidRequestAmount() {
		return bidRequestAmount;
	}

	public void setBidRequestAmount(BigDecimal bidRequestAmount) {
		this.bidRequestAmount = bidRequestAmount;
	}

	public BigDecimal getCurrentRate() {
		return currentRate;
	}

	public void setCurrentRate(BigDecimal currentRate) {
		this.currentRate = currentRate;
	}

	public BigDecimal getMinBidAmount() {
		return minBidAmount;
	}

	public void setMinBidAmount(BigDecimal minBidAmount) {
		this.minBidAmount = minBidAmount;
	}

	public int getMonthes2Return() {
		return monthes2Return;
	}

	public void setMonthes2Return(int monthes2Return) {
		this.monthes2Return = monthes2Return;
	}

	public int getBidCount() {
		return bidCount;
	}

	public void setBidCount(int bidCount) {
		this.bidCount = bidCount;
	}

	public BigDecimal getTotalRewardAmount() {
		return totalRewardAmount;
	}

	public void setTotalRewardAmount(BigDecimal totalRewardAmount) {
		this.totalRewardAmount = totalRewardAmount;
	}

	public BigDecimal getCurrentSum() {
		return currentSum;
	}

	public void setCurrentSum(BigDecimal currentSum) {
		this.currentSum = currentSum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(Date disableDate) {
		this.disableDate = disableDate;
	}

	public int getDisableDays() {
		return disableDays;
	}

	public void setDisableDays(int disableDays) {
		this.disableDays = disableDays;
	}

	public Logininfo getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Logininfo createUser) {
		this.createUser = createUser;
	}

	public List<Bid> getBids() {
		return bids;
	}

	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	//获取剩余还未投满的金额 (+:add  -:subtract * :multiply  / :divide)
	public BigDecimal  getRemainAmount(){
		return this.bidRequestAmount.subtract(this.currentSum);
	}
	
	//获取到进度条
	public BigDecimal getPersent(){
		return this.currentSum.divide(this.bidRequestAmount, BigConst.DISPLAY_SCALE, RoundingMode.HALF_UP);
		
	}
	
	public String getBidRequestStateDisplay() {
		switch (this.bidRequestState) {
		case BIDREQUEST_STATE_PUBLISH_PENDING:
			return "待发布";
		case BIDREQUEST_STATE_BIDDING:
			return "招标中";
		case BIDREQUEST_STATE_UNDO:
			return "已撤销";
		case BIDREQUEST_STATE_BIDDING_OVERDUE:
			return "流标";
		case BIDREQUEST_STATE_APPROVE_PENDING_1:
			return "满标一审";
		case BIDREQUEST_STATE_APPROVE_PENDING_2:
			return "满标二审";
		case BIDREQUEST_STATE_REJECTED:
			return "满标审核被拒";
		case BIDREQUEST_STATE_PAYING_BACK:
			return "还款中";
		case BIDREQUEST_STATE_COMPLETE_PAY_BACK:
			return "完成";
		case BIDREQUEST_STATE_PAY_BACK_OVERDUE:
			return "逾期";
		case BIDREQUEST_STATE_PUBLISH_REFUSE:
			return "发标拒绝";
		default:
			return "";
		}
	}
	
	public String getReturnTypeDisplay() {
		return returnType == BigConst.RETURN_TYPE_MONTH_INTEREST ? "按月到期"
				: "等额本息";
	}

	
	public String getJsonString() {
		Map<String, Object> json = new HashMap<>();
		json.put("id", id);
		json.put("username", this.createUser.getUsername());
		json.put("title", title);
		json.put("bidRequestAmount", bidRequestAmount);
		json.put("currentRate", currentRate);
		json.put("monthes2Return", monthes2Return);
		json.put("returnType", getReturnTypeDisplay());
		json.put("totalRewardAmount", totalRewardAmount);
		return JSONObject.toJSONString(json);
	}

	
}
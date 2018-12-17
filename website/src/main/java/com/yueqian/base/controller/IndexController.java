package com.yueqian.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yueqian.base.util.BigConst;
import com.yueqian.business.domain.BidRequest;
import com.yueqian.business.query.BidRequestQueryObject;
import com.yueqian.business.service.IBidRequestService;

@Controller
public class IndexController {

	@Autowired
	private IBidRequestService bidRequestService;
	@RequestMapping("index")
	public String index(Model model) {
		List<BidRequest> bidRequests = bidRequestService.listIndex(5);
		model.addAttribute("bidRequests", bidRequests);
		return "main";
	}
	
	/**
	 * 投资列表
	 */
	@RequestMapping("invest")
	public String investIndex() {
		return "invest";
	}
	
	@RequestMapping("invest_list")
	public String investList(BidRequestQueryObject qo, Model model) {
		if (qo.getBidRequestState() == -1) {
			qo.setBidRequestStates(new int[] {BigConst.BIDREQUEST_STATE_BIDDING,
					BigConst.BIDREQUEST_STATE_PAYING_BACK,
					BigConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK});
			qo.setBidRequestState(null);
		}
		model.addAttribute("pageResult", bidRequestService.query(qo));
		return "invest_list";
	}
	
	
	
}

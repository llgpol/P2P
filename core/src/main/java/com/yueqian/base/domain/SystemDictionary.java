package com.yueqian.base.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class SystemDictionary extends BaseDomain{

	private String sn;
	private String title;
	private String intro;
	
	
	public String getSn() {
		return sn;
	}


	public void setSn(String sn) {
		this.sn = sn;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getIntro() {
		return intro;
	}


	public void setIntro(String intro) {
		this.intro = intro;
	}

	/**
	 * 返回当前json字符串
	 * @return
	 */
	public String getJsonString() {
		Map< String, Object> map = new HashMap<>();
		map.put("id", this.getId());
		map.put("sn", sn);
		map.put("title", title);
		return JSONObject.toJSONString(map);
	}
	
}

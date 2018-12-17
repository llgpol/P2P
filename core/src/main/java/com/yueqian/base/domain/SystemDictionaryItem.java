package com.yueqian.base.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class SystemDictionaryItem extends BaseDomain{

	private long parentId;
	private String title;
	private int sequence;
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	/**
	 * 返回当前json字符串
	 * @return
	 */
	public String getJsonString() {
		Map< String, Object> map = new HashMap<>();
		map.put("id", this.getId());
		map.put("title", title);
		map.put("sequence", sequence);
		map.put("parentId", parentId);
		return JSONObject.toJSONString(map);
	}
	
}

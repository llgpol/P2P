package com.yueqian.base.domain;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class UserFile extends BaseAuditDomain{

	private String image;
	private SystemDictionaryItem fileType;
	private int score;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public SystemDictionaryItem getFileType() {
		return fileType;
	}
	public void setFileType(SystemDictionaryItem fileType) {
		this.fileType = fileType;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * 返回当前json字符串
	 * 
	 * @return
	 */
	public String getJsonString() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("applier", applier.getUsername());
		map.put("fileType", fileType.getTitle());
		map.put("image", image);
		return JSONObject.toJSONString(map);
	}
	
}

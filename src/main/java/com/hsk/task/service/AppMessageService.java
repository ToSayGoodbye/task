package com.hsk.task.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.hsk.task.bean.AppMessage;
import com.hsk.task.bean.Reply;

public interface AppMessageService {
	List<AppMessage> queryContents(String page,String pagesize,String tab,String latitude,String longitude);
	
	void addInfo(String content, String phone, String openid,String username,String imgUrl,String tab
			,String latitude,String longitude,String address,String photoUrls);

	JSONObject getUserInfo(String js_code, String encryptedData, String iv);
	
	String getOpenid(String js_code);
	
	List<Reply> queryReplys(String id);
	
	int addReply(String openid, String username, String imgUrl, String content, String content_id);
}

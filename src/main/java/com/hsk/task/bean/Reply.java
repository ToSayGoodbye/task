package com.hsk.task.bean;

import lombok.Data;

@Data
public class Reply {
	private int id;
	private String openid;
	private String username;
	private String imgUrl;
	private String time;
	private String content;
	private String reply_id;
	private String content_id;
}

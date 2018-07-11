package com.hsk.task.service;

import com.hsk.task.bean.AppMessage;

public interface AppMessageService {
	
	AppMessage selectByPrimaryKey(String id);

}

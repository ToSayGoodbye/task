package com.hsk.task.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsk.task.bean.AppMessage;
import com.hsk.task.mapper.AppMessageMapper;
import com.hsk.task.service.AppMessageService;

@Service
public class AppMessageServiceImpl implements AppMessageService {

	@Autowired
	AppMessageMapper appMessageMapper;
	
	@Override
	public AppMessage selectByPrimaryKey(String id) {
		
		return appMessageMapper.selectByPrimaryKey(id);
	}

}

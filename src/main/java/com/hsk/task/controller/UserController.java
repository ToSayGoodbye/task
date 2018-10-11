package com.hsk.task.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.hsk.task.service.AppMessageService;

@RestController
public class UserController {
	
	private Logger logger = Logger.getLogger(UserController.class);   
	
	@Autowired
	AppMessageService AppMessageService;
	
	
	@RequestMapping(value = "/getUserInfo")
	public JSONObject getUserInfo(@RequestParam String js_code,@RequestParam String encryptedData,
			@RequestParam String iv){
		return AppMessageService.getUserInfo(js_code, encryptedData, iv);
	}
}

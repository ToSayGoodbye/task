package com.hsk.task.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hsk.task.bean.AppMessage;
import com.hsk.task.service.AppMessageService;
import com.hsk.task.service.QueryQuantityService;

@RestController
public class ExController {
	
	private Logger logger = Logger.getLogger(ExController.class);   
	
	@Autowired
	AppMessageService AppMessageService;
	
	@Autowired
	QueryQuantityService queryQuantityService;
	
	@RequestMapping("/")
	public void test(){
		
		logger.info("定时任务-定时查询除当日的总调用量并入库开始......");
		
		try {
			queryQuantityService.queryQuantityByDate();
		} catch (Exception e) {
			e.printStackTrace();
	        logger.error("定时任务-定时查询除当日的总调用量并入库出现异常：" + e.getMessage(), e);
		}
		
		logger.info("定时任务-定时查询除当日的总调用量并入库结束......");
	}
	
	@Scheduled(cron = "${jobs.schedule}")
    public void scheduled(){
		
		logger.info("定时任务-定时查询除当日的总调用量并入库开始......");
		
		try {
			queryQuantityService.queryQuantityByDate();
		} catch (Exception e) {
			e.printStackTrace();
	        logger.error("定时任务-定时查询除当日的总调用量并入库出现异常：" + e.getMessage(), e);
		}
		
		logger.info("定时任务-定时查询除当日的总调用量并入库结束......");
    }
}

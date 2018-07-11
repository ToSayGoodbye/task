package com.hsk.task.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsk.task.bean.CallQuantity;
import com.hsk.task.mapper.QueryQuantityMapper;
import com.hsk.task.service.QueryQuantityService;
import com.hsk.task.utils.DateUtils;

/**
 * 定时查询除当日的总调用量并入库
 * @author cheng
 *
 */
@Service
public class QueryQuantityServiceImpl implements QueryQuantityService {
	
	private Logger logger = Logger.getLogger(QueryQuantityServiceImpl.class); 
	
	@Autowired
	QueryQuantityMapper queryQuantityMapper;
	
	@Override
	@Transactional(rollbackFor = {IllegalArgumentException.class})
	public void queryQuantityByDate() throws Exception{
		
			//日期格式yyyy-MM-dd 00:00:00
			String nowDay = DateUtils.getNowDate(DateUtils.DATE_FORMAT_8);
			String yesterday = DateUtils.getYesterdayDate(DateUtils.DATE_FORMAT_8);
			
			//查询除当日的调用总量
			List<CallQuantity> totalList = queryQuantityMapper.
					queryQuantityByDate(DateUtils.getNowDate(DateUtils.DATE_FORMAT_8));
			
			//查询前一日调用量
			List<CallQuantity> yesterdayList = queryQuantityMapper.
						queryYesterdayQuantity(yesterday, nowDay);
			
			//批量插入调用总量
			List<HashMap> paramList = new ArrayList<HashMap>();
			for(CallQuantity callQuantity : totalList){
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("merchant_id", callQuantity.getMerch_id());
				map.put("service_id", callQuantity.getDS_NO());
				map.put("cost_group", callQuantity.getCost_group());
				map.put("history_invoke_count", callQuantity.getGroup_count());
				map.put("invoke_day", yesterday);
				map.put("create_user", "system");
				map.put("modify_user", "system");
				paramList.add(map);
			}
			if(paramList.size() > 0){
				queryQuantityMapper.insertQuantity(paramList);
			}
			
			
			//批量更新昨日调用量 
			List<HashMap> paramDayList = new ArrayList<HashMap>();
			for(CallQuantity callQuantity : yesterdayList){
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("merchant_id", callQuantity.getMerch_id());
				map.put("service_id", callQuantity.getDS_NO());
				map.put("day_invoke_count", callQuantity.getGroup_count());
				map.put("invoke_day", yesterday);
				paramDayList.add(map);
			}
			if(paramDayList.size() > 0){
				queryQuantityMapper.updateYesterdayQuantity(paramDayList);
			}
	}
}

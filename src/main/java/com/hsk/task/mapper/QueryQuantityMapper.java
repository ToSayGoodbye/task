package com.hsk.task.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hsk.task.bean.CallQuantity;

public interface QueryQuantityMapper {

	//查询除当日的调用总量
	List<CallQuantity> queryQuantityByDate(String date);
	//查询前一日调用量
	List<CallQuantity> queryYesterdayQuantity(@Param("yesterday")String yesterday,@Param("nowDay")String nowDay);
	//批量插入总量
	int insertQuantity(List list);
	//更新昨日调用量 
	int updateYesterdayQuantity(List list);
}


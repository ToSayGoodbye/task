package com.hsk.task.bean;

public class CallQuantity {
	
	//商户ID
	private String merch_id;
	//计价组
	private String cost_group;
	//服务ID
	private String DS_NO;
	//调用次数
	private String group_count;
	
	public String getMerch_id() {
		return merch_id;
	}
	public void setMerch_id(String merch_id) {
		this.merch_id = merch_id;
	}
	public String getCost_group() {
		return cost_group;
	}
	public void setCost_group(String cost_group) {
		this.cost_group = cost_group;
	}
	public String getDS_NO() {
		return DS_NO;
	}
	public void setDS_NO(String dS_NO) {
		DS_NO = dS_NO;
	}
	public String getGroup_count() {
		return group_count;
	}
	public void setGroup_count(String group_count) {
		this.group_count = group_count;
	}
	
}

package com.hsk.task.bean;

import java.util.List;

public class AppMessage {
	
	private int id;
	
	private String name;
	private String username;
	private String imgUrl;
	private String time;
	private String phone;
	private String content;
	private String address;
	private String reply_count;//回复数
	private String view_count;//回复数
	private String distance;//距离
	private String photos;//图片地址
	private String[] photosList;//图片地址
	
	
	public String[] getPhotosList() {
		return photosList;
	}

	public void setPhotosList(String[] strings) {
		this.photosList = strings;
	}

	public String getPhotos() {
		return photos;
	}

	public void setPhotos(String photos) {
		this.photos = photos;
	}

	public String getView_count() {
		return view_count;
	}

	public void setView_count(String view_count) {
		this.view_count = view_count;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getReply_count() {
		return reply_count;
	}

	public void setReply_count(String reply_count) {
		this.reply_count = reply_count;
	}
	
}

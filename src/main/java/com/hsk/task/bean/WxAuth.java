package com.hsk.task.bean;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component 
@ConfigurationProperties(prefix = "wxapp")
@Data
public class WxAuth {
	private String appId;
	
	private String secret;
	
	private String grantType;
	
	private String sessionHost;
}

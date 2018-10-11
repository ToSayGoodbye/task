package com.hsk.task.serviceImpl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hsk.task.bean.AppMessage;
import com.hsk.task.bean.Reply;
import com.hsk.task.bean.WxAuth;
import com.hsk.task.mapper.AppMessageMapper;
import com.hsk.task.service.AppMessageService;
import com.hsk.task.utils.AES;
import com.hsk.task.utils.HttpRequest;

@Service
public class AppMessageServiceImpl implements AppMessageService {

	@Autowired
	AppMessageMapper appMessageMapper;
	
	@Autowired
	private WxAuth wxAuth;
	
	@Override
	public List<AppMessage> queryContents(String page, String pageSize,String tab,String latitude,String longitude) {
		
		List<AppMessage> list = new ArrayList<AppMessage>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page", Integer.valueOf(page));
		map.put("pagesize", Integer.valueOf(pageSize));
		map.put("tab", Integer.valueOf(tab));
		map.put("latitude", Double.valueOf(latitude));
		map.put("longitude", Double.valueOf(longitude));
		list = appMessageMapper.queryContents(map);
		List<AppMessage> returnList = new ArrayList<AppMessage>();
		//若查询距离最近 则把米换成千米或者米
		for(AppMessage appMessage:list){
			returnList.add(getDistanceStr(appMessage));
		}
		return returnList;
	}
	
	@Override
	public JSONObject getUserInfo(String js_code, String encryptedData, String iv) { 
		
		Map<String,Object> openidMap = this.getWxMap(js_code);
		JSONObject jsonObject = decodeUserInfo(encryptedData,openidMap.get("session_key").toString(),iv);
		System.out.println(jsonObject);
		jsonObject.remove("watermark");
		jsonObject.remove("unionId");
		return jsonObject;
	}
	
	@Override
	public String getOpenid(String js_code) { 
		
		Map<String,Object> openidMap = this.getWxMap(js_code);
		
		return (String) openidMap.get("openid");
	}
	
	/**
	 * 获取openid和sessionkey
	 * @param js_code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object>getWxMap(String js_code){
		StringBuffer sb = new StringBuffer();
		sb.append("appid=").append(wxAuth.getAppId());
		sb.append("&secret=").append(wxAuth.getSecret());
		sb.append("&js_code=").append(js_code);
		sb.append("&grant_type=").append(wxAuth.getGrantType());
		String res = HttpRequest.sendGet(wxAuth.getSessionHost(), sb.toString());
		if(res == null || res.equals("")){
			return null;
		}
		return JSON.parseObject(res, Map.class);
	}
	
	/**
	 * 解密敏感数据包括
	 * @param encryptedData
	 * @param sessionKey
	 * @param iv
	 * @return
	 */
	public JSONObject decodeUserInfo (String encryptedData,String sessionKey,String iv){
		try {
			AES aes = new AES();
			byte[] resultByte = aes.decrypt(Base64.decodeBase64(encryptedData), Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
			if(null != resultByte && resultByte.length > 0){
				String userInfo = new String(resultByte, "UTF-8");
				JSONObject jsonObject; 
				jsonObject = JSONObject.parseObject(userInfo);
				return jsonObject;
			}
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addInfo(String content, String phone, String openid,
			String username, String imgUrl,String tab,String latitude,
			String longitude,String address,String photoUrls) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("content", content);
		paramMap.put("phone", phone);
		paramMap.put("username", username);
		paramMap.put("openid", openid);
		paramMap.put("imgUrl", imgUrl);
		paramMap.put("latitude", latitude);
		paramMap.put("longitude", longitude);
		paramMap.put("address", address);
		paramMap.put("tab", tab);
		paramMap.put("photoUrls", photoUrls);
		
		appMessageMapper.addInfo(paramMap);		
	}

	@Override
	public List<Reply> queryReplys(String id) {
		List<Reply> list = new ArrayList<Reply>();
		list = appMessageMapper.queryReplys(id);
		appMessageMapper.addView(id);
		return list;
	}
	
	/**
	 * 格式化对象（显示米和千米；将图片地址放入list）
	 * @param seller
	 * @return
	 */
	private AppMessage getDistanceStr(AppMessage  seller){
        try{
        	String photos = seller.getPhotos();
        	if(null!=photos && photos.indexOf(".")!=-1){
        		seller.setPhotosList(photos.split(","));
        	}
        	
	        String detail = "";
	        if(null == seller.getDistance()){
	        	return seller;
	        }
	        double dis = Double.parseDouble(seller.getDistance());
	        double tempDis = dis / 1000;
	        detail = String.format("%.1f", tempDis);
	        if(Double.parseDouble(detail) >= 1){
	            detail = detail + "Km";
	            seller.setDistance(detail);
	        }else{
	            seller.setDistance((int) Math.ceil(Double.parseDouble(detail) * 1000) + "m");
	        }
	        AppMessage appMessage = new AppMessage();
	        appMessage = seller;
	        return appMessage;
	       }catch(Exception e){
	    	   e.printStackTrace();
	    	   return seller;
	       }
    }

	@Override
	public int addReply(String openid, String username, String imgUrl,
			String content, String content_id) {
		Map map = new HashMap();
		map.put("openid", openid);
		map.put("username", username);
		map.put("imgUrl", imgUrl);
		map.put("content", content);
		map.put("content_id", content_id);
		int result = appMessageMapper.addReply(map);
		appMessageMapper.addPl(content_id);
		
		return result;
	}


}

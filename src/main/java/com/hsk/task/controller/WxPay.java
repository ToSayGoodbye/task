package com.hsk.task.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hsk.task.bean.WxPayConfig;
import com.hsk.task.service.AppMessageService;
import com.hsk.task.utils.PayUtil;


@RestController
public class WxPay {
	
	@Autowired
	AppMessageService AppMessageService;
	
	@RequestMapping(value = "/getOpenid") 
	public String getUserInfo(@RequestParam String js_code){
		return AppMessageService.getOpenid(js_code);
	}

	/**
	 * @Description: 发起微信支付
	 * @param request
	 */
	@RequestMapping(value = "/wxPay") 
	public Map<String, Object> wxPay(String openid, HttpServletRequest request){
		try{
			//生成的随机字符串
			String nonce_str = getRandomStringByLength(32);
			//商品名称
			String body = "测试商品描述";
			//获取客户端的ip地址
			String spbill_create_ip = getIpAddr(request);
			
			//商户订单号
			String out_trade_no = getRandomStringByLength(8);
			
			//组装参数，用户生成统一下单接口的签名
			Map<String, String> packageParams = new HashMap<String, String>();
			packageParams.put("appid", WxPayConfig.appid);
			packageParams.put("mch_id", WxPayConfig.mch_id);
			packageParams.put("nonce_str", nonce_str);
			packageParams.put("body", body);
			packageParams.put("out_trade_no", out_trade_no);//商户订单号
			packageParams.put("total_fee", "1");//支付金额，这边需要转成字符串类型，否则后面的签名会失败
			packageParams.put("spbill_create_ip", spbill_create_ip);
			packageParams.put("notify_url", WxPayConfig.notify_url);//支付成功后的回调地址
			packageParams.put("trade_type", WxPayConfig.TRADETYPE);//支付方式
			packageParams.put("openid", openid);
			   
        	String prestr = PayUtil.createLinkString(packageParams); // 把数组所有元素，按照“参数=参数值”的模式用“&”字符拼接成字符串 
        
        	//MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
        	String mysign = PayUtil.sign(prestr, WxPayConfig.key, "utf-8").toUpperCase();
	        
	        //拼接统一下单接口使用的xml数据，要将上一步生成的签名一起拼接进去
	        String xml = "<xml>" + "<appid>" + WxPayConfig.appid + "</appid>" 
                    + "<body><![CDATA[" + body + "]]></body>" 
                    + "<mch_id>" + WxPayConfig.mch_id + "</mch_id>" 
                    + "<nonce_str>" + nonce_str + "</nonce_str>" 
                    + "<notify_url>" + WxPayConfig.notify_url + "</notify_url>" 
                    + "<openid>" + openid + "</openid>" 
                    + "<out_trade_no>" + out_trade_no + "</out_trade_no>" 
                    + "<spbill_create_ip>" + spbill_create_ip + "</spbill_create_ip>" 
                    + "<total_fee>" + "1" + "</total_fee>"
                    + "<trade_type>" + WxPayConfig.TRADETYPE + "</trade_type>" 
                    + "<sign>" + mysign + "</sign>"
                    + "</xml>";
	        
	        System.out.println("调试模式_统一下单接口 请求XML数据：" + xml);
 
	        //调用统一下单接口，并接受返回的结果
	        String result = PayUtil.httpRequest(WxPayConfig.pay_url, "POST", xml);
	        
	        System.out.println("调试模式_统一下单接口 返回XML数据：" + result);
	        
	        // 将解析结果存储在HashMap中   
	        Map map = PayUtil.doXMLParse(result);
	        
	        String return_code = (String) map.get("return_code");//返回状态码
	        
		    Map<String, Object> response = new HashMap<String, Object>();//返回给小程序端需要的参数
	        if(return_code.equals("SUCCESS")){   
	            String prepay_id = (String) map.get("prepay_id");//返回的预付单信息   
	            response.put("nonceStr", nonce_str);
	            response.put("package", "prepay_id=" + prepay_id);
	            Long timeStamp = System.currentTimeMillis() / 1000;   
	            response.put("timeStamp", timeStamp + "");//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
	            //拼接签名需要的参数
	            String stringSignTemp = "appId=" + WxPayConfig.appid + "&nonceStr=" + nonce_str + "&package=prepay_id=" + prepay_id+ "&signType=MD5&timeStamp=" + timeStamp;   
	            //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
	            String paySign = PayUtil.sign(stringSignTemp, WxPayConfig.key, "utf-8").toUpperCase();
	            
	            response.put("paySign", paySign);
	        }
			
	        response.put("appid", WxPayConfig.appid);
			
	        return response;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * StringUtils工具类方法
	 * 获取一定长度的随机字符串，范围0-9，a-z
	 * @param length：指定字符串长度
	 * @return 一定长度的随机字符串
	 */
	public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
       }
	/**
	 * IpUtils工具类方法
	 * 获取真实的ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
	    if(!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
	         //多次反向代理后会有多个ip值，第一个ip才是真实ip
	    	int index = ip.indexOf(",");
	        if(index != -1){
	            return ip.substring(0,index);
	        }else{
	            return ip;
	        }
	    }
	    ip = request.getHeader("X-Real-IP");
	    if(!StringUtils.isEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
	       return ip;
	    }
	    return request.getRemoteAddr();
	}
}

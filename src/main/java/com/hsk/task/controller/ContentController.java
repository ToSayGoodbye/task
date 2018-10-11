package com.hsk.task.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.hsk.task.bean.AppMessage;
import com.hsk.task.bean.Reply;
import com.hsk.task.service.AppMessageService;
import com.hsk.task.utils.IDUtil;

@RestController
public class ContentController {
	
	private Logger logger = Logger.getLogger(ContentController.class);   
	
	@Autowired
	AppMessageService AppMessageService;
	
	
	@RequestMapping(value="/queryInfos",method=RequestMethod.GET, produces = "application/json")
	public List<AppMessage> queryInfos(String page,String pagesize,String tab,String latitude,String longitude){
		return AppMessageService.queryContents(page, pagesize,tab,latitude,longitude);
	}
	
	@RequestMapping("/addInfo")
	public void addInfo(String content,String phone,String openid,String username,String imgUrl,String tab,
			String latitude,String longitude,String address,String photos){
		System.out.println(photos);
		JSONArray urls = JSONArray.parseArray(photos);
		StringBuffer photoUrls = new StringBuffer();
		for(int i=0;i<urls.size();i++){
			photoUrls.append(urls.get(i));
			if(i!=urls.size()-1){
				photoUrls.append(",");
			}
		}
		
		AppMessageService.addInfo(content, phone, openid,username,imgUrl,tab,latitude,longitude,address,photoUrls.toString());
	}
	
	/** 
	 * 查询评论
	 * @param id
	 */
	@RequestMapping("/queryReplys")
	public List<Reply> queryInfos(String id){
		return AppMessageService.queryReplys(id);
	}
	
	/**
	 * 添加评论
	 * @param openid
	 * @param username
	 * @param imgUrl
	 * @param content
	 * @param content_id
	 */
	@RequestMapping("/addReply")
	public int addReply(String openid,String username,String imgUrl,
			String content,String content_id){

		return AppMessageService.addReply(openid,username,imgUrl,
				content,content_id);
	}

	
    @RequestMapping("uploadImg")
    public String uploadImg(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        System.out.println("执行upload");
        request.setCharacterEncoding("UTF-8");
        logger.info("执行图片上传");
        String user = request.getParameter("user");
        logger.info("user:"+user);
        String trueFileName;
        if(!file.isEmpty()) {
            logger.info("成功获取照片");
            String fileName = file.getOriginalFilename();
            String path = null;
            String type = null;
            type = fileName.indexOf(".") != -1 ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;
            logger.info("图片初始名称为：" + fileName + " 类型为：" + type);
            if (type != null) {
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    //String realPath = request.getSession().getServletContext().getRealPath("/");
                    // 随机文件名称
                    trueFileName = IDUtil.createID() + "." + type;
                    // 设置存放图片文件的路径
                    path = File.separator+"opt"+File.separator+ "image" + File.separator + trueFileName;
                    logger.info("存放图片文件的路径:" + path);
                    file.transferTo(new File(path));
                    logger.info("文件成功上传到指定目录下");
                }else {
                    logger.info("不是我们想要的文件类型,请按要求重新上传");
                    return "error";
                }
            }else {
                logger.info("文件类型为空");
                return "error";
            }
        }else {
            logger.info("没有找到相对应的文件");
            return "error";
        }
        return trueFileName;
    }

}

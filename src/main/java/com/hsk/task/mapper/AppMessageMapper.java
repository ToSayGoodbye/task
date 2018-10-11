package com.hsk.task.mapper;

import java.util.List;
import java.util.Map;

import com.hsk.task.bean.AppMessage;
import com.hsk.task.bean.Reply;

public interface AppMessageMapper {
    List<AppMessage> queryContents(Map map);
    
    void addInfo(Map map);
    
    List<Reply> queryReplys(String id);
    
    int addReply(Map map);
    
    //增加评论数
    int addPl(String content_id);
    //增加评论数
    int addView(String content_id);
}


package com.hsk.task.mapper;

import com.hsk.task.bean.AppMessage;

public interface AppMessageMapper {

    AppMessage selectByPrimaryKey(String id);
}


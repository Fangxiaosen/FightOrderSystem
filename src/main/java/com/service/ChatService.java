package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.ChatEntity;
import com.utils.PageUtils;

import java.util.Map;

/**
 * 客服聊天 服务类
 */
public interface ChatService extends IService<ChatEntity> {

    /**
     * @param params 查询参数
     * @return 带分页的查询出来的数据
     */
    PageUtils queryPage(Map<String, Object> params);

}
package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ChatDao;
import com.entity.ChatEntity;
import com.entity.view.ChatView;
import com.service.ChatService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 客服聊天 服务实现类
 */
@Service("chatService")
@Transactional
public class ChatServiceImpl extends ServiceImpl<ChatDao, ChatEntity> implements ChatService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ChatView> page = new Query<ChatView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, params));
        return new PageUtils(page);
    }


}

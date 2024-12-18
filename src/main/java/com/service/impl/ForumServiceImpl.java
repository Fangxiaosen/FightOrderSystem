package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.ForumDao;
import com.entity.ForumEntity;
import com.entity.view.ForumView;
import com.service.ForumService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 论坛 服务实现类
 */
@Service("forumService")
@Transactional
public class ForumServiceImpl extends ServiceImpl<ForumDao, ForumEntity> implements ForumService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ForumView> page = new Query<ForumView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, params));
        return new PageUtils(page);
    }


}

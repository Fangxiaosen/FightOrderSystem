package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.NewsDao;
import com.entity.NewsEntity;
import com.entity.view.NewsView;
import com.service.NewsService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 民航新闻 服务实现类
 */
@Service("newsService")
@Transactional
public class NewsServiceImpl extends ServiceImpl<NewsDao, NewsEntity> implements NewsService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NewsView> page = new Query<NewsView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, params));
        return new PageUtils(page);
    }


}

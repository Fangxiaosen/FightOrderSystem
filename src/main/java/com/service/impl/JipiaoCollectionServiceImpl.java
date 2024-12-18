package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.JipiaoCollectionDao;
import com.entity.JipiaoCollectionEntity;
import com.entity.view.JipiaoCollectionView;
import com.service.JipiaoCollectionService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 航班收藏 服务实现类
 */
@Service("jipiaoCollectionService")
@Transactional
public class JipiaoCollectionServiceImpl extends ServiceImpl<JipiaoCollectionDao, JipiaoCollectionEntity> implements JipiaoCollectionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JipiaoCollectionView> page = new Query<JipiaoCollectionView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, params));
        return new PageUtils(page);
    }


}

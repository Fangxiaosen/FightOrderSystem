package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.JipiaoOrderDao;
import com.entity.JipiaoOrderEntity;
import com.entity.view.JipiaoOrderView;
import com.service.JipiaoOrderService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 机票预订 服务实现类
 */
@Service("jipiaoOrderService")
@Transactional
public class JipiaoOrderServiceImpl extends ServiceImpl<JipiaoOrderDao, JipiaoOrderEntity> implements JipiaoOrderService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JipiaoOrderView> page = new Query<JipiaoOrderView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, params));
        return new PageUtils(page);
    }


}

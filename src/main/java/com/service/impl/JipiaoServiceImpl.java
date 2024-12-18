package com.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.JipiaoDao;
import com.entity.JipiaoEntity;
import com.entity.view.JipiaoView;
import com.service.JipiaoService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * 航班信息 服务实现类
 */
@Service("jipiaoService")
@Transactional
public class JipiaoServiceImpl extends ServiceImpl<JipiaoDao, JipiaoEntity> implements JipiaoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JipiaoView> page = new Query<JipiaoView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page, params));
        return new PageUtils(page);
    }


}

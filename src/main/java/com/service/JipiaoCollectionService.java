package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.JipiaoCollectionEntity;
import com.utils.PageUtils;

import java.util.Map;

/**
 * 航班收藏 服务类
 */
public interface JipiaoCollectionService extends IService<JipiaoCollectionEntity> {

    /**
     * @param params 查询参数
     * @return 带分页的查询出来的数据
     */
    PageUtils queryPage(Map<String, Object> params);

}
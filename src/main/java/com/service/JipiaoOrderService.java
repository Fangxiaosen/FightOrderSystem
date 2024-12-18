package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.JipiaoOrderEntity;
import com.utils.PageUtils;

import java.util.Map;

/**
 * 机票预订 服务类
 */
public interface JipiaoOrderService extends IService<JipiaoOrderEntity> {

    /**
     * @param params 查询参数
     * @return 带分页的查询出来的数据
     */
    PageUtils queryPage(Map<String, Object> params);

}
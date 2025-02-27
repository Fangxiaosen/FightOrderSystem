package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.JipiaoEntity;
import com.utils.PageUtils;

import java.util.Map;

/**
 * 航班信息 服务类
 */
public interface JipiaoService extends IService<JipiaoEntity> {

    /**
     * @param params 查询参数
     * @return 带分页的查询出来的数据
     */
    PageUtils queryPage(Map<String, Object> params);

}
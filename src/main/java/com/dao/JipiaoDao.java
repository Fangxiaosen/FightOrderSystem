package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.JipiaoEntity;
import com.entity.view.JipiaoView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 航班信息 Dao 接口
 *
 * @author
 */
public interface JipiaoDao extends BaseMapper<JipiaoEntity> {

    List<JipiaoView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}

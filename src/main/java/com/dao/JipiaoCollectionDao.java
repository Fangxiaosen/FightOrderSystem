package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.JipiaoCollectionEntity;
import com.entity.view.JipiaoCollectionView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 航班收藏 Dao 接口
 *
 * @author
 */
public interface JipiaoCollectionDao extends BaseMapper<JipiaoCollectionEntity> {

    List<JipiaoCollectionView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}

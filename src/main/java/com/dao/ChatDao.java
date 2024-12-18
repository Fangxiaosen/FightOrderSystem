package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.ChatEntity;
import com.entity.view.ChatView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 客服聊天 Dao 接口
 *
 * @author
 */
public interface ChatDao extends BaseMapper<ChatEntity> {

    List<ChatView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}

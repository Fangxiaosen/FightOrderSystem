package com.entity.view;

import com.annotation.ColumnInfo;
import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.NewsEntity;
import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * 民航新闻
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("news")
public class NewsView extends NewsEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //当前表
    /**
     * 新闻类型的值
     */
    @ColumnInfo(comment = "新闻类型的字典表值", type = "varchar(200)")
    private String newsValue;


    public NewsView() {

    }

    public NewsView(NewsEntity newsEntity) {
        try {
            BeanUtils.copyProperties(this, newsEntity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    //当前表的

    /**
     * 获取： 新闻类型的值
     */
    public String getNewsValue() {
        return newsValue;
    }

    /**
     * 设置： 新闻类型的值
     */
    public void setNewsValue(String newsValue) {
        this.newsValue = newsValue;
    }


    @Override
    public String toString() {
        return "NewsView{" +
                ", newsValue=" + newsValue +
                "} " + super.toString();
    }
}

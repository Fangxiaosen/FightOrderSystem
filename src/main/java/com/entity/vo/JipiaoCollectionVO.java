package com.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 航班收藏
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jipiao_collection")
public class JipiaoCollectionVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 航班
     */

    @TableField(value = "jipiao_id")
    private Integer jipiaoId;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 类型
     */

    @TableField(value = "jipiao_collection_types")
    private Integer jipiaoCollectionTypes;


    /**
     * 收藏时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 photoShow
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
     * 设置：主键
     */
    public Integer getId() {
        return id;
    }


    /**
     * 获取：主键
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 设置：航班
     */
    public Integer getJipiaoId() {
        return jipiaoId;
    }


    /**
     * 获取：航班
     */

    public void setJipiaoId(Integer jipiaoId) {
        this.jipiaoId = jipiaoId;
    }

    /**
     * 设置：用户
     */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
     * 获取：用户
     */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }

    /**
     * 设置：类型
     */
    public Integer getJipiaoCollectionTypes() {
        return jipiaoCollectionTypes;
    }


    /**
     * 获取：类型
     */

    public void setJipiaoCollectionTypes(Integer jipiaoCollectionTypes) {
        this.jipiaoCollectionTypes = jipiaoCollectionTypes;
    }

    /**
     * 设置：收藏时间
     */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
     * 获取：收藏时间
     */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * 设置：创建时间 show3 photoShow
     */
    public Date getCreateTime() {
        return createTime;
    }


    /**
     * 获取：创建时间 show3 photoShow
     */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

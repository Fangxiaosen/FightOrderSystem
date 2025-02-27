package com.entity;

import com.annotation.ColumnInfo;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.utils.DateUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;


/**
 * 航班收藏
 *
 * @author
 * @email
 */
@TableName("jipiao_collection")
public class JipiaoCollectionEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment = "主键", type = "int(11)")
    @TableField(value = "id")

    private Integer id;
    /**
     * 航班
     */
    @ColumnInfo(comment = "航班", type = "int(11)")
    @TableField(value = "jipiao_id")

    private Integer jipiaoId;
    /**
     * 用户
     */
    @ColumnInfo(comment = "用户", type = "int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;
    /**
     * 类型
     */
    @ColumnInfo(comment = "类型", type = "int(11)")
    @TableField(value = "jipiao_collection_types")

    private Integer jipiaoCollectionTypes;
    /**
     * 收藏时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @ColumnInfo(comment = "收藏时间", type = "timestamp")
    @TableField(value = "insert_time", fill = FieldFill.INSERT)

    private Date insertTime;
    /**
     * 创建时间
     */
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    @ColumnInfo(comment = "创建时间", type = "timestamp")
    @TableField(value = "create_time", fill = FieldFill.INSERT)

    private Date createTime;


    public JipiaoCollectionEntity() {

    }


    public JipiaoCollectionEntity(T t) {
        try {
            BeanUtils.copyProperties(this, t);
        } catch (IllegalAccessException | InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取：主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：主键
     */

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：航班
     */
    public Integer getJipiaoId() {
        return jipiaoId;
    }

    /**
     * 设置：航班
     */

    public void setJipiaoId(Integer jipiaoId) {
        this.jipiaoId = jipiaoId;
    }

    /**
     * 获取：用户
     */
    public Integer getYonghuId() {
        return yonghuId;
    }

    /**
     * 设置：用户
     */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }

    /**
     * 获取：类型
     */
    public Integer getJipiaoCollectionTypes() {
        return jipiaoCollectionTypes;
    }

    /**
     * 设置：类型
     */

    public void setJipiaoCollectionTypes(Integer jipiaoCollectionTypes) {
        this.jipiaoCollectionTypes = jipiaoCollectionTypes;
    }

    /**
     * 获取：收藏时间
     */
    public Date getInsertTime() {
        return insertTime;
    }

    /**
     * 设置：收藏时间
     */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "JipiaoCollection{" +
                ", id=" + id +
                ", jipiaoId=" + jipiaoId +
                ", yonghuId=" + yonghuId +
                ", jipiaoCollectionTypes=" + jipiaoCollectionTypes +
                ", insertTime=" + DateUtil.convertString(insertTime, "yyyy-MM-dd") +
                ", createTime=" + DateUtil.convertString(createTime, "yyyy-MM-dd") +
                "}";
    }
}

package com.spring.demo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-09
 */
@TableName("system_config")
@ApiModel(value = "")
public class SystemConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "",name = "id")
    private Integer id;
    /**
     * key值
     */
    @ApiModelProperty(value = "key值",name = "configKey")
    private String configKey;
    /**
     * value值
     */
    @ApiModelProperty(value = "value值",name = "configValue")
    private String configValue;
    @ApiModelProperty(value = "",name = "createTime")
    private Date createTime;
    @ApiModelProperty(value = "",name = "updateTime")
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SystemConfig{" +
        ", id=" + id +
        ", configKey=" + configKey +
        ", configValue=" + configValue +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

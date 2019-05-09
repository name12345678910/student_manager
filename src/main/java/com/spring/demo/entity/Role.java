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
@TableName("role")
@ApiModel(value = "")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "",name = "id")
    private Integer id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称",name = "roleName")
    private String roleName;
    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述",name = "descpt")
    private String descpt;
    /**
     * 角色编号
     */
    @ApiModelProperty(value = "角色编号",name = "code")
    private String code;
    /**
     * 操作用户id
     */
    @ApiModelProperty(value = "操作用户id",name = "insertUid")
    private Integer insertUid;
    /**
     * 添加数据时间
     */
    @ApiModelProperty(value = "添加数据时间",name = "insertTime")
    private Date insertTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",name = "updateTime")
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescpt() {
        return descpt;
    }

    public void setDescpt(String descpt) {
        this.descpt = descpt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getInsertUid() {
        return insertUid;
    }

    public void setInsertUid(Integer insertUid) {
        this.insertUid = insertUid;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Role{" +
        ", id=" + id +
        ", roleName=" + roleName +
        ", descpt=" + descpt +
        ", code=" + code +
        ", insertUid=" + insertUid +
        ", insertTime=" + insertTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

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
@TableName("permission")
@ApiModel(value = "")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "",name = "id")
    private Integer id;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称",name = "name")
    private String name;
    /**
     * 父菜单id
     */
    @ApiModelProperty(value = "父菜单id",name = "pid")
    private Integer pid;
    /**
     * 菜单排序
     */
    @ApiModelProperty(value = "菜单排序",name = "zindex")
    private Integer zindex;
    /**
     * 权限分类（0 菜单；1 功能）
     */
    @ApiModelProperty(value = "权限分类（0 菜单；1 功能）",name = "istype")
    private Integer istype;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述",name = "descpt")
    private String descpt;
    /**
     * 菜单编号
     */
    @ApiModelProperty(value = "菜单编号",name = "code")
    private String code;
    /**
     * 菜单图标名称
     */
    @ApiModelProperty(value = "菜单图标名称",name = "icon")
    private String icon;
    /**
     * 菜单url
     */
    @ApiModelProperty(value = "菜单url",name = "page")
    private String page;
    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间",name = "insertTime")
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getZindex() {
        return zindex;
    }

    public void setZindex(Integer zindex) {
        this.zindex = zindex;
    }

    public Integer getIstype() {
        return istype;
    }

    public void setIstype(Integer istype) {
        this.istype = istype;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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
        return "Permission{" +
        ", id=" + id +
        ", name=" + name +
        ", pid=" + pid +
        ", zindex=" + zindex +
        ", istype=" + istype +
        ", descpt=" + descpt +
        ", code=" + code +
        ", icon=" + icon +
        ", page=" + page +
        ", insertTime=" + insertTime +
        ", updateTime=" + updateTime +
        "}";
    }
}

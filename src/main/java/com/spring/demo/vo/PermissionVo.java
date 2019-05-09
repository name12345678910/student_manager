package com.spring.demo.vo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.spring.demo.entity.Permission;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

public class PermissionVo implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "", name = "id")
    private Integer id;
    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称", name = "name")
    private String name;
    /**
     * 父菜单id
     */
    @ApiModelProperty(value = "父菜单id", name = "pid")
    private Integer pid;
    /**
     * 菜单排序
     */
    @ApiModelProperty(value = "菜单排序", name = "zindex")
    private Integer zindex;
    /**
     * 权限分类（0 菜单；1 功能）
     */
    @ApiModelProperty(value = "权限分类（0 菜单；1 功能）", name = "istype")
    private Integer istype;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "descpt")
    private String descpt;
    /**
     * 菜单编号
     */
    @ApiModelProperty(value = "菜单编号", name = "code")
    private String code;
    /**
     * 菜单图标名称
     */
    @ApiModelProperty(value = "菜单图标名称", name = "icon")
    private String icon;

    private List<Permission> children;

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

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PermissionVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", zindex=" + zindex +
                ", istype=" + istype +
                ", descpt='" + descpt + '\'' +
                ", code='" + code + '\'' +
                ", icon='" + icon + '\'' +
                ", children=" + children +
                '}';
    }
}

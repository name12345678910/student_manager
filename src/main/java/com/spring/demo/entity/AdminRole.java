package com.spring.demo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-09
 */
@TableName("admin_role")
@ApiModel(value = "")
public class AdminRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "",name = "id")
    private Integer id;
    @ApiModelProperty(value = "",name = "adminId")
    private Integer adminId;
    @ApiModelProperty(value = "",name = "roleId")
    private Integer roleId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "AdminRole{" +
        ", id=" + id +
        ", adminId=" + adminId +
        ", roleId=" + roleId +
        "}";
    }
}

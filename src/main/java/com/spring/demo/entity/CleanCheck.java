package com.spring.demo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 *
 *
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
@TableName("clean_check")
@ApiModel(value = "")
public class CleanCheck implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 卫生检查表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "卫生检查表id", name = "id")
    private Integer id;
    /**
     * 宿舍id
     */
    @ApiModelProperty(value = "宿舍id", name = "dormRoomId")
    @NotNull(message = "宿舍不能为空")
    private Integer dormRoomId;
    /**
     * 检查人
     */
    @ApiModelProperty(value = "检查人", name = "dormRoomAdminId")
    @NotNull(message = "检查人不能为空")
    private Integer dormRoomAdminId;
    /**
     * 检查周数
     */
    @ApiModelProperty(value = "检查周数", name = "checkTime")
    @NotNull(message = "检查周数不能为空")
    private Integer checkTime;
    /**
     * 成绩
     */
    @ApiModelProperty(value = "成绩", name = "grade")
    @NotNull(message = "成绩不能为空")
    private BigDecimal grade;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description")
    @NotBlank(message = "描述不能为空")
    private String description;
    @ApiModelProperty(value = "", name = "createTime")
    private Date createTime;
    @ApiModelProperty(value = "", name = "updateTime")
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDormRoomAdminId() {
        return dormRoomAdminId;
    }

    public void setDormRoomAdminId(Integer dormRoomAdminId) {
        this.dormRoomAdminId = dormRoomAdminId;
    }

    public Integer getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Integer checkTime) {
        this.checkTime = checkTime;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getDormRoomId() {
        return dormRoomId;
    }

    public void setDormRoomId(Integer dormRoomId) {
        this.dormRoomId = dormRoomId;
    }

    @Override
    public String toString() {
        return "CleanCheck{" +
                "id=" + id +
                ", dormRoomId=" + dormRoomId +
                ", dormRoomAdminId=" + dormRoomAdminId +
                ", checkTime=" + checkTime +
                ", grade=" + grade +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

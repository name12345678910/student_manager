package com.spring.demo.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@TableName("disciplinary_action")
@ApiModel(value = "")
public class DisciplinaryAction implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 违纪处分表id
	 */
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(value = "违纪处分表id", name = "id")
	private Integer id;
	@ApiModelProperty(value = "", name = "studentId")
	@NotNull(message = "学生ID不能为空")
	private Integer studentId;
	/**
	 * 管理员id
	 */
	@ApiModelProperty(value = "管理员id", name = "dormRoomAdminId")
	@NotNull(message = "管理员id不能为空")
	private Integer dormRoomAdminId;
	/**
	 * 违纪时间
	 */
	@ApiModelProperty(value = "违纪时间", name = "recordTime")
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date recordTime;
	/**
	 * 1-晚归 0-违纪
	 */
	@ApiModelProperty(value = "1-晚归 0-违纪", name = "disciplinaryType")
	@NotNull(message = "违规类型不能为空")
	private Integer disciplinaryType;
	/**
	 * 违规描述
	 */
	@ApiModelProperty(value = "违规描述", name = "description")
	@NotBlank(message = "违规描述不能为空")
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

	

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getDormRoomAdminId() {
		return dormRoomAdminId;
	}

	public void setDormRoomAdminId(Integer dormRoomAdminId) {
		this.dormRoomAdminId = dormRoomAdminId;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public Integer getDisciplinaryType() {
		return disciplinaryType;
	}

	public void setDisciplinaryType(Integer disciplinaryType) {
		this.disciplinaryType = disciplinaryType;
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

	@Override
	public String toString() {
		return "DisciplinaryAction{" + ", id=" + id + ", studentId=" + studentId + ", dormRoomAdminId="
				+ dormRoomAdminId + ", recordTime=" + recordTime + ", disciplinaryType=" + disciplinaryType
				+ ", description=" + description + ", createTime=" + createTime + ", updateTime=" + updateTime + "}";
	}
}

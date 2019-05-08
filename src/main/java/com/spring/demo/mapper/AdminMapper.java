package com.spring.demo.mapper;

import com.spring.demo.entity.Admin;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
public interface AdminMapper extends BaseMapper<Admin> {
	 List<Admin> getPageAdminList(Page<Admin> page, @Param("nickName") String nickName);
}

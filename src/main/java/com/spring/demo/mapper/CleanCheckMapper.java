package com.spring.demo.mapper;

import com.spring.demo.entity.CleanCheck;

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
public interface CleanCheckMapper extends BaseMapper<CleanCheck> {
	 List<CleanCheck> getPageCleanCheckList(Page<CleanCheck> page, @Param("dormRoomId") Integer dormRoomId);
}

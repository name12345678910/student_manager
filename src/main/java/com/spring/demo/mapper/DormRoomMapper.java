package com.spring.demo.mapper;

import com.spring.demo.entity.Admin;
import com.spring.demo.entity.DormRoom;

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
public interface DormRoomMapper extends BaseMapper<DormRoom> {
	 List<DormRoom> getPageDormRoomList(Page<DormRoom> page, @Param("dormRoomNo") String dormRoomNo);
}

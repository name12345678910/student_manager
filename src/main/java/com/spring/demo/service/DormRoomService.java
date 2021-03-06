package com.spring.demo.service;

import com.spring.demo.entity.DormRoom;
import com.spring.demo.util.PageDataResult;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
public interface DormRoomService extends IService<DormRoom> {
	 PageDataResult getPageDormRoomList(Integer page, Integer limit, String dormRoomNo);
}

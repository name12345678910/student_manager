package com.spring.demo.service;

import com.spring.demo.entity.Fee;
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
public interface FeeService extends IService<Fee> {
	 PageDataResult getPageFeeList(Integer page, Integer limit, Integer dormRoomId);
}

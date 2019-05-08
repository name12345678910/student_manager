package com.spring.demo.service;

import com.spring.demo.entity.Dorm;
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
public interface DormService extends IService<Dorm> {
	 PageDataResult getPageDormList(Integer page, Integer limit, String dormNo, String dormName);
}

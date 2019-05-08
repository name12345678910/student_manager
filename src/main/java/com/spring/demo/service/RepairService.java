package com.spring.demo.service;

import com.spring.demo.entity.Repair;
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
public interface RepairService extends IService<Repair> {
	  PageDataResult getPageRepairList(Integer page, Integer limit, String repairName,Integer dormRoomId);
}

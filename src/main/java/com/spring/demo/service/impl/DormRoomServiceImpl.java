package com.spring.demo.service.impl;

import com.spring.demo.entity.Admin;
import com.spring.demo.entity.DormRoom;
import com.spring.demo.mapper.DormRoomMapper;
import com.spring.demo.service.DormRoomService;
import com.spring.demo.util.PageDataResult;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
@Service
public class DormRoomServiceImpl extends ServiceImpl<DormRoomMapper, DormRoom> implements DormRoomService {

	@Override
	public PageDataResult getPageDormRoomList(Integer page, Integer limit, String dormRoomNo) {
		 Page<DormRoom> p = new Page<>(page, limit);
	        p.setRecords(baseMapper.getPageDormRoomList(p, dormRoomNo));
	        PageDataResult pdr = new PageDataResult(p.getTotal(), p.getRecords());
	        return pdr;
	}

}

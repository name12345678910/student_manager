package com.spring.demo.service.impl;

import com.spring.demo.entity.Admin;
import com.spring.demo.entity.Fee;
import com.spring.demo.mapper.FeeMapper;
import com.spring.demo.service.FeeService;
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
public class FeeServiceImpl extends ServiceImpl<FeeMapper, Fee> implements FeeService {

	@Override
	public PageDataResult getPageFeeList(Integer page, Integer limit, Integer dormRoomId) {
		 Page<Fee> p = new Page<>(page, limit);
	        p.setRecords(baseMapper.getPageFeeList(p, dormRoomId));
	        PageDataResult pdr = new PageDataResult(p.getTotal(), p.getRecords());
	        return pdr;
	}

}

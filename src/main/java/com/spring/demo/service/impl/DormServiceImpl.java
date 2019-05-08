package com.spring.demo.service.impl;

import com.spring.demo.entity.Admin;
import com.spring.demo.entity.Dorm;
import com.spring.demo.mapper.DormMapper;
import com.spring.demo.service.DormService;
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
public class DormServiceImpl extends ServiceImpl<DormMapper, Dorm> implements DormService {

	@Override
	public PageDataResult getPageDormList(Integer page, Integer limit, String dormNo, String dormName) {
		  Page<Dorm> p = new Page<>(page, limit);
	        p.setRecords(baseMapper.getPageDormList(p, dormNo,dormName));
	        PageDataResult pdr = new PageDataResult(p.getTotal(), p.getRecords());
	        return pdr;
	}

}

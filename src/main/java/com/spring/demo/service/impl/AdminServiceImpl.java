package com.spring.demo.service.impl;

import com.spring.demo.entity.Admin;
import com.spring.demo.mapper.AdminMapper;
import com.spring.demo.service.AdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

	@Override
	public PageDataResult getPageAdminList(Integer page, Integer limit, String nickName) {
		  Page<Admin> p = new Page<>(page, limit);
	        p.setRecords(baseMapper.getPageAdminList(p, nickName));
	        PageDataResult pdr = new PageDataResult(p.getTotal(), p.getRecords());
	        return pdr;
	}

}

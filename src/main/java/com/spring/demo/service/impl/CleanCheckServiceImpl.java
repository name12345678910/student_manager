package com.spring.demo.service.impl;
import com.spring.demo.entity.CleanCheck;
import com.spring.demo.mapper.CleanCheckMapper;
import com.spring.demo.service.CleanCheckService;
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
public class CleanCheckServiceImpl extends ServiceImpl<CleanCheckMapper, CleanCheck> implements CleanCheckService {

	@Override
	public PageDataResult getPageCleanCheckList(Integer page, Integer limit, Integer dormRoomId) {
		 Page<CleanCheck> p = new Page<>(page, limit);
	        p.setRecords(baseMapper.getPageCleanCheckList(p, dormRoomId));
	        PageDataResult pdr = new PageDataResult(p.getTotal(), p.getRecords());
	        return pdr;
	}

}

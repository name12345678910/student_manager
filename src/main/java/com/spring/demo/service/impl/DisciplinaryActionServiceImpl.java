package com.spring.demo.service.impl;

import com.spring.demo.entity.DisciplinaryAction;
import com.spring.demo.mapper.DisciplinaryActionMapper;
import com.spring.demo.service.DisciplinaryActionService;
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
public class DisciplinaryActionServiceImpl extends ServiceImpl<DisciplinaryActionMapper, DisciplinaryAction> implements DisciplinaryActionService {

	@Override
	public PageDataResult getPageDisciplinaryActionList(Integer page, Integer limit, Integer studentId) {
		 Page<DisciplinaryAction> p = new Page<>(page, limit);
	        p.setRecords(baseMapper.getPageDisciplinaryActionList(p, studentId));
	        PageDataResult pdr = new PageDataResult(p.getTotal(), p.getRecords());
	        return pdr;
	}

}

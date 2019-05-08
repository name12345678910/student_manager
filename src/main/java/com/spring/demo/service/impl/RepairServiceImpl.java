package com.spring.demo.service.impl;

import com.spring.demo.entity.Repair;
import com.spring.demo.mapper.RepairMapper;
import com.spring.demo.service.RepairService;
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
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {

	@Override
	public PageDataResult getPageRepairList(Integer page, Integer limit, String repairName, Integer dormRoomId) {
		Page<Repair> p = new Page<>(page, limit);
        p.setRecords(baseMapper.getPageRepairList(p, repairName,dormRoomId));
        PageDataResult pdr = new PageDataResult(p.getTotal(), p.getRecords());
        return pdr;
	}

}

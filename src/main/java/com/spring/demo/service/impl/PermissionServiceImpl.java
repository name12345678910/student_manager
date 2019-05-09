package com.spring.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.spring.demo.entity.Permission;
import com.spring.demo.mapper.PermissionMapper;
import com.spring.demo.service.PermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spring.demo.util.PageDataResult;
import com.spring.demo.vo.PermissionVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-09
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<PermissionVo> getPermissionVo(List<String> ids) {
        return baseMapper.getPermissionVo(ids);
    }

    @Override
    public PageDataResult getEditPermissionList(Integer page, Integer limit, Integer roleId) {
        Page<Map<String, Object>> p = new Page<>(page, limit);
        p.setRecords(baseMapper.getEditPermissionList(p, roleId));
        PageDataResult pdr = new PageDataResult(p.getTotal(), p.getRecords());
        return pdr;
    }
}

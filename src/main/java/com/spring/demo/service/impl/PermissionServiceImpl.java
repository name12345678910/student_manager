package com.spring.demo.service.impl;

import com.spring.demo.entity.Permission;
import com.spring.demo.mapper.PermissionMapper;
import com.spring.demo.service.PermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spring.demo.vo.PermissionVo;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<PermissionVo> getPermissionVo() {
        return baseMapper.getPermissionVo();
    }
}

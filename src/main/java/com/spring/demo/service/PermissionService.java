package com.spring.demo.service;

import com.spring.demo.entity.Permission;
import com.baomidou.mybatisplus.service.IService;
import com.spring.demo.vo.PermissionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-09
 */
public interface PermissionService extends IService<Permission> {

    List<PermissionVo> getPermissionVo();
}

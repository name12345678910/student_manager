package com.spring.demo.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.spring.demo.entity.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spring.demo.vo.PermissionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-09
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<PermissionVo> getPermissionVo(@Param("ids") List<String> ids);

    List<Permission> getPermissionListByPid(@Param("pid") Integer pid);

    List<Map<String, Object>> getEditPermissionList(Page<Map<String, Object>> p, @Param("roleId") Integer roleId);
}

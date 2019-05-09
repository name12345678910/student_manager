package com.spring.demo.mapper;

import com.spring.demo.entity.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spring.demo.vo.PermissionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-09
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<PermissionVo> getPermissionVo();

    List<Permission> getPermissionListByPid(@Param("pid") Integer pid);
}

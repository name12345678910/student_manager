package com.spring.demo.mapper;

import com.spring.demo.entity.DisciplinaryAction;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
public interface DisciplinaryActionMapper extends BaseMapper<DisciplinaryAction> {

    List<DisciplinaryAction> getPageDisciplinaryActionList(Page<DisciplinaryAction> p, @Param("studentId") Integer studentId);
}

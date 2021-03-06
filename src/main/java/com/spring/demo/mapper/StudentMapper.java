package com.spring.demo.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.spring.demo.entity.Student;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spring.demo.entity.Visitor;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
public interface StudentMapper extends BaseMapper<Student> {

    List<Map<String, Object>> getPageStudentList(Page<Map<String, Object>> p, @Param("studentName") String studentName);

    List<Map<String, Object>> getStudentList(@Param("studentIds") List<String> studentIds);
}

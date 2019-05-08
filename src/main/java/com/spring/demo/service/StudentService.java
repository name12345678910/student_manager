package com.spring.demo.service;

import com.spring.demo.constant.UcenterResult;
import com.spring.demo.entity.Student;
import com.baomidou.mybatisplus.service.IService;
import com.spring.demo.exception.GloBalException;
import com.spring.demo.util.PageDataResult;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
public interface StudentService extends IService<Student> {

    PageDataResult getPageStudentList(Integer page, Integer limit, String studentName);

    List<Map<String, Object>> getStudentList(String[] studentIdList);

    void uploadStudent(List<Map<String, Object>> data) throws GloBalException;
}

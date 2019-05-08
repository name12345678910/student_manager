package com.spring.demo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.spring.demo.entity.Student;
import com.spring.demo.entity.Visitor;
import com.spring.demo.mapper.StudentMapper;
import com.spring.demo.service.StudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spring.demo.util.PageDataResult;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public PageDataResult getPageStudentList(Integer page, Integer limit, String studentName) {
        Page<Visitor> p = new Page<>(page, limit);
        p.setRecords(baseMapper.getPageStudentList(p, studentName));
        PageDataResult pdr = new PageDataResult(p.getTotal(), p.getRecords());
        return pdr;
    }
}

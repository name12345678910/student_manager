package com.spring.demo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.*;
import com.spring.demo.exception.GloBalException;
import com.spring.demo.mapper.DormRoomMapper;
import com.spring.demo.mapper.MajorMapper;
import com.spring.demo.mapper.StuClassMapper;
import com.spring.demo.mapper.StudentMapper;
import com.spring.demo.service.StudentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spring.demo.util.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private StuClassMapper stuClassMapper;
    @Autowired
    private MajorMapper majorMapper;
    @Autowired
    private DormRoomMapper dormRoomMapper;


    @Override
    public PageDataResult getPageStudentList(Integer page, Integer limit, String studentName) {
        Page<Map<String, Object>> p = new Page<>(page, limit);
        p.setRecords(baseMapper.getPageStudentList(p, studentName));
        PageDataResult pdr = new PageDataResult(p.getTotal(), p.getRecords());
        return pdr;
    }

    @Override
    public List<Map<String, Object>> getStudentList(String[] studentIdList) {
        List<Map<String, Object>> studentList = baseMapper.getStudentList(Arrays.asList(studentIdList));
        return studentList;
    }

    @Transactional
    @Override
    public void uploadStudent(List<Map<String, Object>> data) throws GloBalException {
        StuClass stuClass = new StuClass();
        Major major = new Major();
        DormRoom dormRoom = new DormRoom();
        Dorm dorm = new Dorm();
        for (int i = 0; i < data.size(); i++) {

            EntityWrapper<Student> studentEntityWrapper = new EntityWrapper<>();
            studentEntityWrapper.eq("stu_no", data.get(i).get("stu_no"));
            List<Student> studentList = baseMapper.selectList(studentEntityWrapper);
            if (studentList.size() != 0) {
                throw new GloBalException(new UcenterResult("学号已存在--" + data.get(i).get("stu_no")));
            }

            EntityWrapper<StuClass> stuClassEntityWrapper = new EntityWrapper<>();
            stuClassEntityWrapper.eq("class_name", data.get(i).get("class_name"));
            List<StuClass> stuClassList = stuClassMapper.selectList(stuClassEntityWrapper);
            if (stuClassList.size() == 0) {
                throw new GloBalException(new UcenterResult("班级不存在--" + data.get(i).get("class_name")));
            } else {
                stuClass = stuClassList.get(0);
            }

            EntityWrapper<Major> majorEntityWrapper = new EntityWrapper<>();
            majorEntityWrapper.eq("major_name", data.get(i).get("major_name"));
            List<Major> majorList = majorMapper.selectList(majorEntityWrapper);
            if (majorList.size() == 0) {
                throw new GloBalException(new UcenterResult("专业不存在--" + data.get(i).get("major_name")));
            } else {
                major = majorList.get(0);
            }

            EntityWrapper<DormRoom> dormRoomEntityWrapper = new EntityWrapper<>();
            dormRoomEntityWrapper.eq("dorm_room_no", data.get(i).get("dorm_room_no"));
            List<DormRoom> dormRoomList = dormRoomMapper.selectList(dormRoomEntityWrapper);
            if (dormRoomList.size() == 0) {
                throw new GloBalException(new UcenterResult("宿舍不存在--" + data.get(i).get("dorm_room_no")));
            } else {
                dormRoom = dormRoomList.get(0);
            }

            Student student = new Student();
            student.setMajorId(major.getId());
            student.setCreateTime(new Date());
            student.setStuClassId(stuClass.getId());
            student.setStuName((String) data.get(i).get("stu_name"));
            student.setStuNo((String) data.get(i).get("stu_no"));
            student.setStuDormRoomId(String.valueOf(dormRoom.getId()));
            student.setStuPhone((String) data.get(i).get("stu_phone"));
            student.setStuAge(18);
            student.setStuPwd((String) data.get(i).get("stu_no"));
            baseMapper.insert(student);
        }
    }
}

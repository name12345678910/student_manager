package com.spring.demo.controller.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.*;
import com.spring.demo.service.*;
import com.spring.demo.util.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StuClassService stuClassService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DormService dormService;
    @Autowired
    private DormRoomService dormRoomService;

    @GetMapping("/getStuByDormRoomId")
    @ResponseBody
    public UcenterResult getStuByDormRoomId(@RequestParam("dormRoomId") Integer dormRoomId) {
        EntityWrapper<Student> studentEntityWrapper = new EntityWrapper<>();
        studentEntityWrapper.eq("stu_dorm_room_id", dormRoomId);
        List<Student> studentList = studentService.selectList(studentEntityWrapper);
        return new UcenterResult(UcenterResultConstant.SUCCESS, studentList);
    }

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/studentList")
    public String index() {
        return "student/studentList";
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @param studentName
     * @return
     */
    @GetMapping("/getStudentList")
    @ResponseBody
    public PageDataResult getVisitorList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         @RequestParam(value = "studentName", required = false) String studentName) {
        PageDataResult pdr = null;
        pdr = studentService.getPageStudentList(page, limit, studentName);
        return pdr;
    }

    /**
     * 跳转增加页面
     *
     * @return
     */
    @GetMapping("/toAddStudent")
    public String toAddVisitor() {
        return "student/addStudent";
    }

    /**
     * @return
     */
    @PostMapping("/addStudent")
    @ResponseBody
    public UcenterResult addVisitor(@Validated Student student) {
        //设置访问时间为当天
        student.setCreateTime(new Date());
        studentService.insert(student);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转修改页面
     *
     * @return
     */
    @GetMapping("/toEditStudent")
    public ModelAndView toEditVisitor(@RequestParam("studentId") Integer studentId) {
        ModelAndView mav = new ModelAndView("student/editStudent");
        Student student = studentService.selectById(studentId);
        StuClass stuClass = stuClassService.selectById(student.getStuClassId());
        Department department = departmentService.selectById(stuClass.getDepartmentId());
        DormRoom dormRoom = dormRoomService.selectById(student.getStuDormRoomId());
        Dorm dorm = dormService.selectById(dormRoom.getDormId());
        mav.addObject("student", student);
        mav.addObject("stuClass", stuClass);
        mav.addObject("department", department);
        mav.addObject("dormRoom", dormRoom);
        mav.addObject("dorm", dorm);
        return mav;
    }

    /**
     * 修改visitor
     *
     * @return
     */
    @PostMapping("/editStudent")
    @ResponseBody
    public UcenterResult editVisitor(@Validated Student student) {
        studentService.updateById(student);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 单个删除
     *
     * @return
     */
    @PostMapping("/delStudentById")
    @ResponseBody
    public UcenterResult delVisitor(@RequestParam("studentId") Integer studentId) {
        studentService.deleteById(studentId);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 批量删除
     *
     * @return
     */
    @PostMapping("/delStudents")
    @ResponseBody
    public UcenterResult delVisitors(@RequestParam("studentIds") String studentIds) {
        String[] studentIdList = studentIds.split(",");
        studentService.deleteBatchIds(Arrays.asList(studentIdList));
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

}


package com.spring.demo.controller.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.*;
import com.spring.demo.exception.GloBalException;
import com.spring.demo.service.*;
import com.spring.demo.util.ExcelReadHelper;
import com.spring.demo.util.ExcelUtils;
import com.spring.demo.util.PageDataResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
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
    @Autowired
    private MajorService majorService;

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
    public UcenterResult addVisitor(@RequestParam("stuNo") String stuNo,
                                    @RequestParam("stuName") String stuName,
                                    @RequestParam("stuPhone") String stuPhone,
                                    @RequestParam("className") String className,
                                    @RequestParam("majorName") String majorName,
                                    @RequestParam("dormRoomNo") String dormRoomNo) {
        StuClass stuClass = new StuClass();
        Major major = new Major();
        DormRoom dormRoom = new DormRoom();
        Dorm dorm = new Dorm();

        EntityWrapper<Student> studentEntityWrapper = new EntityWrapper<>();
        studentEntityWrapper.eq("stu_no", stuNo);
        List<Student> studentList = studentService.selectList(studentEntityWrapper);
        if (studentList.size() != 0) {
            throw new GloBalException(new UcenterResult("学号已存在--" + stuNo));
        }

        EntityWrapper<StuClass> stuClassEntityWrapper = new EntityWrapper<>();
        stuClassEntityWrapper.eq("class_name", className);
        List<StuClass> stuClassList = stuClassService.selectList(stuClassEntityWrapper);
        if (stuClassList.size() == 0) {
            throw new GloBalException(new UcenterResult("班级不存在--" + className));
        } else {
            stuClass = stuClassList.get(0);
        }

        EntityWrapper<Major> majorEntityWrapper = new EntityWrapper<>();
        majorEntityWrapper.eq("major_name", majorName);
        List<Major> majorList = majorService.selectList(majorEntityWrapper);
        if (majorList.size() == 0) {
            throw new GloBalException(new UcenterResult("专业不存在--" + majorName));
        } else {
            major = majorList.get(0);
        }

        EntityWrapper<DormRoom> dormRoomEntityWrapper = new EntityWrapper<>();
        dormRoomEntityWrapper.eq("dorm_room_no", dormRoomNo);
        List<DormRoom> dormRoomList = dormRoomService.selectList(dormRoomEntityWrapper);
        if (dormRoomList.size() == 0) {
            throw new GloBalException(new UcenterResult("宿舍不存在--" + dormRoomNo));
        } else {
            dormRoom = dormRoomList.get(0);
        }

        Student student = new Student();
        student.setMajorId(major.getId());
        student.setCreateTime(new Date());
        student.setStuClassId(stuClass.getId());
        student.setStuName(stuName);
        student.setStuNo(stuNo);
        student.setStuDormRoomId(String.valueOf(dormRoom.getId()));
        student.setStuPhone(stuPhone);
        student.setStuAge(18);
        student.setStuPwd(stuNo);
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
        Major major = majorService.selectById(student.getMajorId());
        mav.addObject("student", student);
        mav.addObject("stuClass", stuClass);
        mav.addObject("department", department);
        mav.addObject("dormRoom", dormRoom);
        mav.addObject("dorm", dorm);
        mav.addObject("major", major);
        return mav;
    }

    /**
     * 修改visitor
     *
     * @return
     */
    @PostMapping("/editStudent")
    @ResponseBody
    public UcenterResult editVisitor(@RequestParam("studentId") Integer studentId,
                                     @RequestParam("stuName") String stuName,
                                     @RequestParam("stuPhone") String stuPhone,
                                     @RequestParam("className") String className,
                                     @RequestParam("majorName") String majorName,
                                     @RequestParam("dormRoomNo") String dormRoomNo) {
        Student student = studentService.selectById(studentId);

        if (student == null) {
            throw new GloBalException(new UcenterResult("该学生不存在"));
        }

        StuClass stuClass = new StuClass();
        Major major = new Major();
        DormRoom dormRoom = new DormRoom();
        Dorm dorm = new Dorm();

        EntityWrapper<StuClass> stuClassEntityWrapper = new EntityWrapper<>();
        stuClassEntityWrapper.eq("class_name", className);
        List<StuClass> stuClassList = stuClassService.selectList(stuClassEntityWrapper);
        if (stuClassList.size() == 0) {
            throw new GloBalException(new UcenterResult("班级不存在--" + className));
        } else {
            stuClass = stuClassList.get(0);
        }

        EntityWrapper<Major> majorEntityWrapper = new EntityWrapper<>();
        majorEntityWrapper.eq("major_name", majorName);
        List<Major> majorList = majorService.selectList(majorEntityWrapper);
        if (majorList.size() == 0) {
            throw new GloBalException(new UcenterResult("专业不存在--" + majorName));
        } else {
            major = majorList.get(0);
        }

        EntityWrapper<DormRoom> dormRoomEntityWrapper = new EntityWrapper<>();
        dormRoomEntityWrapper.eq("dorm_room_no", dormRoomNo);
        List<DormRoom> dormRoomList = dormRoomService.selectList(dormRoomEntityWrapper);
        if (dormRoomList.size() == 0) {
            throw new GloBalException(new UcenterResult("宿舍不存在--" + dormRoomNo));
        } else {
            dormRoom = dormRoomList.get(0);
        }

        student.setMajorId(major.getId());
        student.setUpdateTime(new Date());
        student.setStuClassId(stuClass.getId());
        student.setStuName(stuName);
        student.setStuDormRoomId(String.valueOf(dormRoom.getId()));
        student.setStuPhone(stuPhone);
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

    /**
     * 批量下载
     *
     * @return
     */
    @GetMapping("/excelStudentList")
    @ResponseBody
    public void excelStudentList(HttpServletResponse responsea, @RequestParam("studentIds") String studentIds) throws IOException {
        String[] studentIdList = studentIds.split(",");
        List<Map<String, Object>> data = studentService.getStudentList(studentIdList);
        //对应map的keys
        String[] fieldNames = new String[]{"stu_no", "stu_name", "stu_phone", "class_name", "major_name", "dorm_name", "dorm_room_no"};
        //表头
        String[] titles = new String[]{"学生编号", "学生姓名", "学生电话", "学生班级", "学生专业", "宿舍楼名称", "宿舍编号"};
        String sheetName = "sheet1";
        //exportExcel(String sheetName, List<Map<String, Object>> list, String[] titles, String[] fieldNames)
        HSSFWorkbook wb = ExcelUtils.exportExcel(sheetName, data, titles, fieldNames);
        ExcelUtils.export(responsea, wb, "student.xls");
    }

    /**
     * 批量上传
     *
     * @return
     */
    @PostMapping("/uploadStudentList")
    @ResponseBody
    public UcenterResult uploadStudentList(MultipartFile file) throws Exception {
        //对应map的keys
        String[] fieldNames = new String[]{"stu_no", "stu_name", "stu_phone", "class_name", "major_name", "dorm_name", "dorm_room_no"};
        InputStream backStream = file.getInputStream();
        List<Map<String, Object>> data = ExcelReadHelper.getBantchByExcel(backStream, file.getOriginalFilename(), fieldNames);
        studentService.uploadStudent(data);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }
}


package com.spring.demo.controller.web;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.*;
import com.spring.demo.service.*;
import com.spring.demo.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private RoleService roleService;
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

    @GetMapping("/toLogin_admin")
    public String toLogin1() {
        return "login";
    }

    @GetMapping("/adminLogout")
    public String adminLogout() {
        return "login";
    }

    @GetMapping("/toLogin_student")
    public String toLogin2() {
        return "login2";
    }

    @GetMapping("/stuLogout")
    public String stuLogout() {
        return "login2";
    }

    @PostMapping("/login")
    @ResponseBody
    public UcenterResult login(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("loginType") Integer loginType) {
        Map<String, Object> data = new HashMap<>();
        data.put("loginType", loginType);
        Student student = null;
        Admin admin = null;
        if (1 == loginType) {
            EntityWrapper<Admin> adminEntityWrapper = new EntityWrapper<>();
            adminEntityWrapper.eq("login_name", username);
            adminEntityWrapper.eq("passwd", password);
            List<Admin> adminList = adminService.selectList(adminEntityWrapper);
            if (adminList.size() != 0) {
                admin = adminList.get(0);
            } else {
                return new UcenterResult("账号密码错误，请重新登陆");
            }
            data.put("admin", admin);
        } else {
            EntityWrapper<Student> adminEntityWrapper = new EntityWrapper<>();
            adminEntityWrapper.eq("stu_no", username);
            adminEntityWrapper.eq("stu_pwd", password);
            List<Student> studentList = studentService.selectList(adminEntityWrapper);
            if (studentList.size() != 0) {
                student = studentList.get(0);
            } else {
                return new UcenterResult("账号密码错误，请重新登陆");
            }
            data.put("student", student);
        }
        return new UcenterResult(UcenterResultConstant.SUCCESS, data);
    }

    @RequestMapping("/index")
    public ModelAndView index(@RequestParam("userId") Integer userId, @RequestParam("loginType") Integer loginType) {
        ModelAndView mav = null;
        if (2 == loginType) {
            mav = new ModelAndView("stuIndex");
            Student student = studentService.selectById(userId);
            mav.addObject("student", student);
        } else {
            mav = new ModelAndView("index3");
            //根据userid获得角色，然后获得权限ids
            EntityWrapper<AdminRole> adminRoleEntityWrapper = new EntityWrapper<>();
            adminRoleEntityWrapper.eq("admin_id", userId);
            List<AdminRole> adminRoleList = adminRoleService.selectList(adminRoleEntityWrapper);
            if (adminRoleList.size() != 0) {
                Integer roleId = adminRoleList.get(0).getRoleId();
                EntityWrapper<RolePermission> rolePermissionEntityWrapper = new EntityWrapper<>();
                rolePermissionEntityWrapper.eq("role_id", roleId);
                List<RolePermission> rolePermissionList = rolePermissionService.selectList(rolePermissionEntityWrapper);
                if (rolePermissionList.size() != 0) {
                    List<String> ids = new ArrayList<>(rolePermissionList.size());
                    for (int i = 0; i < rolePermissionList.size(); i++) {
                        ids.add(String.valueOf(rolePermissionList.get(i).getPermitId()));
                    }
                    List<PermissionVo> permissionVoList = permissionService.getPermissionVo(ids);
                    for (int j = 0; j < permissionVoList.size(); j++) {
                        List<Permission> children = permissionVoList.get(j).getPermissionList();
                        for (int k = 0; k < children.size(); k++) {
                            boolean flag = true;
                            for (int m = 0; m < ids.size(); m++) {
                                if (ids.get(m).equals(String.valueOf(children.get(k).getId()))) {
                                    flag = false;
                                    break;
                                }
                            }
                            if (flag) {
                                children.remove(k);
                                k--;
                            }
                        }
                        permissionVoList.get(j).setPermissionList(children);
                    }
                    mav.addObject("permissionVoList", permissionVoList);
                }
            }
            Admin admin = adminService.selectById(userId);
            mav.addObject("admin", admin);
        }
        return mav;
    }

    @GetMapping("/toEditPwd")
    public ModelAndView toEditPwd(@RequestParam("userId") Integer userId) {
        ModelAndView mav = new ModelAndView("editPwd");
        mav.addObject("userId", userId);
        return mav;
    }

    @GetMapping("/toEditPwd2")
    public ModelAndView toEditPwd2(@RequestParam("userId") Integer userId) {
        ModelAndView mav = new ModelAndView("editPwdStu");
        mav.addObject("userId", userId);
        return mav;
    }

    @PostMapping("/student/editPwd")
    @ResponseBody
    public UcenterResult editPwd(@RequestParam("userId") Integer userId,
                                 @RequestParam("oldPwd") String oldPwd,
                                 @RequestParam("newPwd") String newPwd,
                                 @RequestParam("newPwd2") String newPwd2) {
        Student student = studentService.selectById(userId);
        if (!newPwd.equals(newPwd2)) {
            return new UcenterResult("两次输入密码不相同");
        }
        if (!oldPwd.equals(student.getStuPwd())) {
            return new UcenterResult("原密码不正确");
        }
        student.setStuPwd(newPwd);
        studentService.updateById(student);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    @PostMapping("/admin/editPwd")
    @ResponseBody
    public UcenterResult editPwd2(@RequestParam("userId") Integer userId,
                                 @RequestParam("oldPwd") String oldPwd,
                                 @RequestParam("newPwd") String newPwd,
                                 @RequestParam("newPwd2") String newPwd2) {
        Admin admin = adminService.selectById(userId);
        if (!newPwd.equals(newPwd2)) {
            return new UcenterResult("两次输入密码不相同");
        }
        if (!oldPwd.equals(admin.getPasswd())) {
            return new UcenterResult("原密码不正确");
        }
        admin.setPasswd(newPwd);
        adminService.updateById(admin);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }


    @GetMapping("/toUserInfo")
    public ModelAndView toUserInfo(@RequestParam("userId") Integer userId) {
        ModelAndView mav = new ModelAndView("userInfo");
        Admin admin = adminService.selectById(userId);
        EntityWrapper<AdminRole> adminRoleEntityWrapper = new EntityWrapper<>();
        adminRoleEntityWrapper.eq("admin_id", userId);
        List<AdminRole> adminRoleList = adminRoleService.selectList(adminRoleEntityWrapper);
        mav.addObject("admin", admin);
        mav.addObject("role", roleService.selectById(adminRoleList.get(0).getRoleId()));
        return mav;
    }

    @GetMapping("/toUserInfo2")
    public ModelAndView toUserInfo2(@RequestParam("userId") Integer userId) {
        ModelAndView mav = new ModelAndView("userInfo2");
        Student student = studentService.selectById(userId);
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
}

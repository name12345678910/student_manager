package com.spring.demo.controller.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.spring.demo.entity.AdminRole;
import com.spring.demo.entity.Role;
import com.spring.demo.service.AdminRoleService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.Admin;
import com.spring.demo.service.AdminService;
import com.spring.demo.util.PageDataResult;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdminRoleService adminRoleService;

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/adminList")
    public String index() {
        return "admin/adminList";
    }

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/toAddRole")
    public ModelAndView toAddRole(@RequestParam("adminId") Integer adminId) {
        ModelAndView mac = new ModelAndView("admin/addRole");
        mac.addObject("adminId", adminId);
        return mac;
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @param
     * @return
     */
    @GetMapping("/getAdminList")
    @ResponseBody
    public PageDataResult getAdminList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                       @RequestParam(value = "nickname", required = false) String nickname) {
        PageDataResult pdr = null;
        pdr = adminService.getPageAdminList(page, limit, nickname);
        return pdr;
    }

    /**
     * 跳转增加页面
     *
     * @return
     */
    @GetMapping("/toAddAdmin")
    public String toAddVisitor() {
        return "admin/addAdmin";
    }

    /**
     * 添加visitor
     *
     * @param
     * @return
     */
    @PostMapping("/addAdmin")
    @ResponseBody
    public UcenterResult addVisitor(@Validated Admin admin) {
        admin.setAdminStatus(1);
        admin.setCreateTime(new Date());
        adminService.insert(admin);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转修改页面
     *
     * @param
     * @return
     */
    @GetMapping("/toEditAdmin")
    public ModelAndView toEditVisitor(@RequestParam("adminId") Integer adminId) {
        System.out.println(adminId);
        ModelAndView mav = new ModelAndView("admin/editAdmin");
        Admin admin = adminService.selectById(adminId);
        mav.addObject("admin", admin);
        return mav;
    }

    /**
     * 修改visitor
     *
     * @param
     * @return
     */
    @PostMapping("/editAdmin")
    @ResponseBody
    public UcenterResult editVisitor(@Validated Admin admin) {
        admin.setUpdateTime(new Date());
        adminService.updateById(admin);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 单个删除
     *
     * @param
     * @return
     */
    @PostMapping("/delAdminById")
    @ResponseBody
    public UcenterResult delAdmin(@RequestParam("adminId") Integer adminId) {
        adminService.deleteById(adminId);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 批量删除
     *
     * @param
     * @return
     */
    @PostMapping("/delAdmins")
    @ResponseBody
    public UcenterResult delAdmins(@RequestParam("adminIds") String adminIds) {
        String[] adminIdList = adminIds.split(",");
        adminService.deleteBatchIds(Arrays.asList(adminIdList));
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转查看页面
     *
     * @param
     * @return
     */
    @GetMapping("/lookAdmin")
    public ModelAndView lookVisitor(@RequestParam("adminId") Integer adminId) {
        ModelAndView mav = new ModelAndView("admin/lookAdmin");
        Admin admin = adminService.selectById(adminId);
        mav.addObject("admin", admin);
        return mav;
    }

    /**
     * layui分页查询
     *
     * @return
     */
    @PostMapping("/addAdminRole")
    @ResponseBody
    public UcenterResult addRole(@RequestParam("adminId") Integer adminId,
                                 @RequestParam("roleId") Integer roleId) {
        EntityWrapper<AdminRole> adminRoleEntityWrapper = new EntityWrapper<>();
        adminRoleEntityWrapper.eq("admin_id", adminId);
        adminRoleEntityWrapper.eq("role_id", roleId);
        List<AdminRole> adminRoleList = adminRoleService.selectList(adminRoleEntityWrapper);
        if (adminRoleList.size() != 0) {
            return new UcenterResult(UcenterResultConstant.SUCCESS, null);
        }
        AdminRole adminRole = new AdminRole();
        adminRole.setAdminId(adminId);
        adminRole.setRoleId(roleId);
        adminRoleService.insert(adminRole);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }
}



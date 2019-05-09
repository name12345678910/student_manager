package com.spring.demo.controller.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.Permission;
import com.spring.demo.entity.Role;
import com.spring.demo.entity.RolePermission;
import com.spring.demo.service.PermissionService;
import com.spring.demo.service.RolePermissionService;
import com.spring.demo.util.PageDataResult;
import com.spring.demo.vo.PermissionVo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-09
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("/getPermissionVo")
    @ResponseBody
    public UcenterResult getPermissionVo() {
        List<PermissionVo> permissionVoList = permissionService.getPermissionVo();
        return new UcenterResult(UcenterResultConstant.SUCCESS, permissionVoList);
    }

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/permissionList")
    public String index() {
        return "permission/permissionList";
    }

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/editPermissionList")
    public ModelAndView editPermissionList(@RequestParam("roleId") Integer roleId) {
        ModelAndView mav = new ModelAndView("role/editPermissionList");
        mav.addObject("roleId", roleId);
        return mav;
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getPermissionList")
    @ResponseBody
    public PageDataResult getVisitorList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        PageDataResult pdr = null;
        Page<Permission> p = new Page<>(page, limit);
        Page<Permission> permissionPage = permissionService.selectPage(p);
        pdr = new PageDataResult(permissionPage.getTotal(), permissionPage.getRecords());
        return pdr;
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getEditPermissionList")
    @ResponseBody
    public PageDataResult getEditPermissionList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "roleId") Integer roleId,
                                                @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        PageDataResult pdr = null;
        pdr = permissionService.getEditPermissionList(page, limit, roleId);
        return pdr;
    }

    @PostMapping("/delPermission")
    @ResponseBody
    public UcenterResult delPermission(@RequestParam("roleId") Integer roleId,
                                       @RequestParam("permissionId") Integer permissionId) {
        EntityWrapper<RolePermission> rolePermissionEntityWrapper = new EntityWrapper<>();
        rolePermissionEntityWrapper.eq("permit_id", permissionId);
        rolePermissionEntityWrapper.eq("role_id", roleId);
        rolePermissionService.delete(rolePermissionEntityWrapper);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    @PostMapping("/addPermission")
    @ResponseBody
    public UcenterResult addPermission(@RequestParam("roleId") Integer roleId,
                                       @RequestParam("permissionId") Integer permissionId) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setPermitId(permissionId);
        rolePermission.setRoleId(roleId);
        rolePermissionService.insert(rolePermission);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }
}


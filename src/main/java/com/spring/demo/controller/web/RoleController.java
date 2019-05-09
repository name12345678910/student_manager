package com.spring.demo.controller.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.Role;
import com.spring.demo.service.RoleService;
import com.spring.demo.util.PageDataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/roleList")
    public String index() {
        return "role/roleList";
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("/getRoleList")
    @ResponseBody
    public PageDataResult getVisitorList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        PageDataResult pdr = null;
        Page<Role> p = new Page<>(page, limit);
        Page<Role> rolePage = roleService.selectPage(p);
        pdr = new PageDataResult(rolePage.getTotal(), rolePage.getRecords());
        return pdr;
    }

    /**
     * layui分页查询
     *
     * @return
     */
    @GetMapping("/getRoleList2")
    @ResponseBody
    public UcenterResult getRoleList2() {
        List<Role> roleList = roleService.selectList(new EntityWrapper<Role>());
        return new UcenterResult(UcenterResultConstant.SUCCESS, roleList);
    }

}


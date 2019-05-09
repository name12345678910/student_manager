package com.spring.demo.controller.web;


import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.service.PermissionService;
import com.spring.demo.vo.PermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/getPermissionVo")
    @ResponseBody
    public UcenterResult getPermissionVo() {
        List<PermissionVo> permissionVoList = permissionService.getPermissionVo();
        return new UcenterResult(UcenterResultConstant.SUCCESS, permissionVoList);
    }
}


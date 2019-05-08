package com.spring.demo.controller.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.Dorm;
import com.spring.demo.service.DormService;
import com.spring.demo.util.PageDataResult;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
@Controller
@RequestMapping("/dorm")
public class DormController {
	@Autowired
    private DormService dormService;

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/dormList")
    public String index() {
        return "dorm/dormList";
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @param
     * @return
     */
    @GetMapping("/getDormList")
    @ResponseBody
    public PageDataResult getDormList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         @RequestParam(value = "dormNo", required = false) String dormNo,
                                         @RequestParam(value = "dormName", required = false) String dormName) {
        PageDataResult pdr = null;
        pdr = dormService.getPageDormList(page, limit, dormNo,dormName);
        return pdr;
    }

    /**
     * 跳转增加页面
     *
     * @return
     */
    @GetMapping("/toAddDorm")
    public String toAddVisitor() {
        return "dorm/addDorm";
    }

    /**
     * 添加visitor
     *
     * @param
     * @return
     */
    @PostMapping("/addDorm")
    @ResponseBody
    public UcenterResult addVisitor(@Validated Dorm dorm) {
    	dorm.setCreateTime(new Date());
    	dormService.insert(dorm);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转修改页面
     *
     * @param
     * @return
     */
    @GetMapping("/toEditDorm")
    public ModelAndView toEditVisitor(@RequestParam("dormId") Integer dormId) {
        ModelAndView mav = new ModelAndView("dorm/editDorm");
        Dorm dorm = dormService.selectById(dormId);
        mav.addObject("dorm", dorm);
        return mav;
    }

    /**
     * 修改visitor
     *
     * @param
     * @return
     */
    @PostMapping("/editDorm")
    @ResponseBody
    public UcenterResult editVisitor(@Validated Dorm dorm) {
    	dorm.setUpdateTime(new Date());
    	dormService.updateById(dorm);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 单个删除
     *
     * @param
     * @return
     */
    @PostMapping("/delDormById")
    @ResponseBody
    public UcenterResult delDorm(@RequestParam("dormId") Integer dormId) {
    	dormService.deleteById(dormId);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 批量删除
     *
     * @param
     * @return
     */
    @PostMapping("/delDorms")
    @ResponseBody
    public UcenterResult delDorms(@RequestParam("dormIds") String dormIds) {
        String[] dormIdList = dormIds.split(",");
        dormService.deleteBatchIds(Arrays.asList(dormIdList));
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转查看页面
     *
     * @return
     */
    @GetMapping("/lookDorm")
    public ModelAndView lookVisitor(@RequestParam("dormId") Integer dormId) {
        ModelAndView mav = new ModelAndView("dorm/lookDorm");
        Dorm dorm = dormService.selectById(dormId);
        mav.addObject("dorm", dorm);
        return mav;
    }
}


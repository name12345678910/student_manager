package com.spring.demo.controller.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.CleanCheck;
import com.spring.demo.service.CleanCheckService;
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
@RequestMapping("/cleanCheck")
public class CleanCheckController {
	@Autowired
    private CleanCheckService cleanCheckService;

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/cleanCheckList")
    public String index() {
        return "cleanCheck/cleanCheckList";
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @param visitorName
     * @return
     */
    @GetMapping("/getCleanCheckList")
    @ResponseBody
    public PageDataResult getCleanCheckList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         @RequestParam(value = "dormRoomId", required = false) Integer dormRoomId) {
        PageDataResult pdr = null;
        pdr = cleanCheckService.getPageCleanCheckList(page, limit, dormRoomId);
        return pdr;
    }

    /**
     * 跳转增加页面
     *
     * @return
     */
    @GetMapping("/toAddCleanCheck")
    public String toAddVisitor() {
        return "cleanCheck/addCleanCheck";
    }

    /**
     * 添加visitor
     *
     * @param visitor
     * @return
     */
    @PostMapping("/addCleanCheck")
    @ResponseBody
    public UcenterResult addVisitor(@Validated CleanCheck cleanCheck) {
    	cleanCheck.setCreateTime(new Date());
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转修改页面
     *
     * @param visitorId
     * @return
     */
    @GetMapping("/toEditCleanCheck")
    public ModelAndView toEditVisitor(@RequestParam("cleanCheckId") Integer cleanCheckId) {
        ModelAndView mav = new ModelAndView("cleanCheck/editCleanCheck");
        CleanCheck cleanCheck = cleanCheckService.selectById(cleanCheckId);
        mav.addObject("cleanCheck", cleanCheck);
        return mav;
    }

    /**
     * 修改visitor
     *
     * @param visitor
     * @return
     */
    @PostMapping("/editCleanCheck")
    @ResponseBody
    public UcenterResult editVisitor(@Validated CleanCheck cleanCheck) {
    	cleanCheck.setUpdateTime(new Date());
    	cleanCheckService.updateById(cleanCheck);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 单个删除
     *
     * @param visitorId
     * @return
     */
    @PostMapping("/delCleanCheckById")
    @ResponseBody
    public UcenterResult delCleanCheck(@RequestParam("cleanCheckId") Integer cleanCheckId) {
    	cleanCheckService.deleteById(cleanCheckId);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 批量删除
     *
     * @param visitorIds
     * @return
     */
    @PostMapping("/delCleanChecks")
    @ResponseBody
    public UcenterResult delCleanChecks(@RequestParam("cleanCheckIds") String cleanCheckIds) {
        String[] cleanCheckIdList = cleanCheckIds.split(",");
        cleanCheckService.deleteBatchIds(Arrays.asList(cleanCheckIdList));
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转查看页面
     *
     * @param visitorId
     * @return
     */
    @GetMapping("/lookCleanCheck")
    public ModelAndView lookVisitor(@RequestParam("cleanCheckId") Integer cleanCheckId) {
        ModelAndView mav = new ModelAndView("cleanCheck/lookCleanCheck");
        CleanCheck cleanCheck = cleanCheckService.selectById(cleanCheckId);
        mav.addObject("cleanCheck", cleanCheck);
        return mav;
    }
}


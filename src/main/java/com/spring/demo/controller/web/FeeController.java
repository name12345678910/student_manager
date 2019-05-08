package com.spring.demo.controller.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.Fee;
import com.spring.demo.service.FeeService;
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
@RequestMapping("/fee")
public class FeeController {
	@Autowired
    private FeeService feeService;

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/feeList")
    public String index() {
        return "fee/feeList";
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @param
     * @return
     */
    @GetMapping("/getFeeList")
    @ResponseBody
    public PageDataResult getFeeList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         @RequestParam(value = "dormRoomId", required = false) Integer dormRoomId) {
        PageDataResult pdr = null;
        pdr = feeService.getPageFeeList(page, limit, dormRoomId);
        return pdr;
    }

    /**
     * 跳转增加页面
     *
     * @return
     */
    @GetMapping("/toAddFee")
    public String toAddVisitor() {
        return "fee/addFee";
    }

    /**
     * 添加visitor
     *
     * @param
     * @return
     */
    @PostMapping("/addFee")
    @ResponseBody
    public UcenterResult addVisitor(@Validated Fee fee) {
    	fee.setStatus(0);
    	fee.setCreateTime(new Date());
    	feeService.insert(fee);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转修改页面
     *
     * @param
     * @return
     */
    @GetMapping("/toEditFee")
    public ModelAndView toEditVisitor(@RequestParam("feeId") Integer feeId) {
        ModelAndView mav = new ModelAndView("fee/editFee");
        Fee fee = feeService.selectById(feeId);
        mav.addObject("fee", fee);
        return mav;
    }

    /**
     * 修改visitor
     *
     * @param
     * @return
     */
    @PostMapping("/editFee")
    @ResponseBody
    public UcenterResult editVisitor(@Validated Fee fee) {
    	fee.setUpdateTime(new Date());
    	feeService.updateById(fee);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 单个删除
     *
     * @param
     * @return
     */
    @PostMapping("/delFeeById")
    @ResponseBody
    public UcenterResult delFee(@RequestParam("feeId") Integer feeId) {
    	feeService.deleteById(feeId);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 批量删除
     *
     * @param
     * @return
     */
    @PostMapping("/delFees")
    @ResponseBody
    public UcenterResult delFees(@RequestParam("feeIds") String feeIds) {
        String[] feeIdList = feeIds.split(",");
        feeService.deleteBatchIds(Arrays.asList(feeIdList));
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转查看页面
     *
     * @param
     * @return
     */
    @GetMapping("/lookFee")
    public ModelAndView lookVisitor(@RequestParam("feeId") Integer feeId) {
        ModelAndView mav = new ModelAndView("Fee/lookFee");
        Fee fee = feeService.selectById(feeId);
        mav.addObject("fee", fee);
        return mav;
    }
}


package com.spring.demo.controller.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.Repair;
import com.spring.demo.service.RepairService;
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
@RequestMapping("/repair")
public class RepairController {
	@Autowired
    private RepairService repairService;

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/repairList")
    public String index() {
        return "repair/repairList";
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @param repairName
     * @return
     */
    @GetMapping("/getRepairList")
    @ResponseBody
    public PageDataResult getRepairList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         @RequestParam(value = "repairName", required = false) String repairName,
                                         @RequestParam(value = "dormRoomId", required = false) Integer dormRoomId) {
        PageDataResult pdr = null;
        pdr = repairService.getPageRepairList(page, limit, repairName, dormRoomId);
        return pdr;
    }

    /**
     * 跳转增加页面
     *
     * @return
     */
    @GetMapping("/toAddRepair")
    public String toAddRepair() {
        return "repair/addRepair";
    }

    /**
     * 添加repair
     *
     * @param repair
     * @return
     */
    @PostMapping("/addRepair")
    @ResponseBody
    public UcenterResult addRepair(@Validated Repair repair) {
    	repair.setCreateTime(new Date());
        repair.setRepairTime(new Date());
        repair.setStatus(0);
        repairService.insert(repair);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转修改页面
     *
     * @param repairId
     * @return
     */
    @GetMapping("/toEditRepair")
    public ModelAndView toEditRepair(@RequestParam("repairId") Integer repairId) {
        ModelAndView mav = new ModelAndView("repair/editRepair");
        Repair repair = repairService.selectById(repairId);
        mav.addObject("repair", repair);
        return mav;
    }

    /**
     * 修改repair
     *
     * @param repair
     * @return
     */
    @PostMapping("/editRepair")
    @ResponseBody
    public UcenterResult editRepair(@Validated Repair repair) {
        repair.setUpdateTime(new Date());
        repairService.updateById(repair);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 单个删除
     *
     * @param repairId
     * @return
     */
    @PostMapping("/delRepairById")
    @ResponseBody
    public UcenterResult delrepair(@RequestParam("repairId") Integer repairId) {
        repairService.deleteById(repairId);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 批量删除
     *
     * @param repairIds
     * @return
     */
    @PostMapping("/delRepairs")
    @ResponseBody
    public UcenterResult delRepairs(@RequestParam("repairIds") String repairIds) {
        String[] repairIdList = repairIds.split(",");
        repairService.deleteBatchIds(Arrays.asList(repairIdList));
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转查看页面
     *
     * @param repairId
     * @return
     */
    @GetMapping("/lookRepair")
    public ModelAndView lookRepair(@RequestParam("repairId") Integer repairId) {
        ModelAndView mav = new ModelAndView("repair/lookRepair");
        Repair repair = repairService.selectById(repairId);
        mav.addObject("repair", repair);
        return mav;
    }
}


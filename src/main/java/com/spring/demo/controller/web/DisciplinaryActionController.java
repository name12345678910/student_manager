package com.spring.demo.controller.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.DisciplinaryAction;
import com.spring.demo.service.DisciplinaryActionService;
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
@RequestMapping("/disciplinaryAction")
public class DisciplinaryActionController {
	@Autowired
    private DisciplinaryActionService disciplinaryActionService;

    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/disciplinaryActionList")
    public String index() {
        return "disciplinaryAction/disciplinaryActionList";
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @param
     * @return
     */
    @GetMapping("/getDisciplinaryActionList")
    @ResponseBody
    public PageDataResult getDisciplinaryActionList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         @RequestParam(value = "studentId", required = false) Integer studentId) {
        PageDataResult pdr = null;
        pdr = disciplinaryActionService.getPageDisciplinaryActionList(page, limit, studentId);
        return pdr;
    }

    /**
     * 跳转增加页面
     *
     * @return
     */
    @GetMapping("/toAddDisciplinaryAction")
    public String toAddVisitor() {
        return "DisciplinaryAction/addDisciplinaryAction";
    }

    /**
     * 添加visitor
     *
     * @param
     * @return
     */
    @PostMapping("/addDisciplinaryAction")
    @ResponseBody
    public UcenterResult addVisitor(@Validated DisciplinaryAction disciplinaryAction) {
    	disciplinaryAction.setRecordTime(new Date());
    	disciplinaryAction.setCreateTime(new Date());
    	disciplinaryActionService.insert(disciplinaryAction);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转修改页面
     *
     * @param
     * @return
     */
    @GetMapping("/toEditDisciplinaryAction")
    public ModelAndView toEditVisitor(@RequestParam("disciplinaryActionId") Integer disciplinaryActionId) {
        ModelAndView mav = new ModelAndView("disciplinaryAction/editDisciplinaryAction");
        DisciplinaryAction disciplinaryAction = disciplinaryActionService.selectById(disciplinaryActionId);
        mav.addObject("disciplinaryAction", disciplinaryAction);
        return mav;
    }

    /**
     * 修改visitor
     *
     * @param
     * @return
     */
    @PostMapping("/editDisciplinaryAction")
    @ResponseBody
    public UcenterResult editVisitor(@Validated DisciplinaryAction disciplinaryAction) {
    	disciplinaryAction.setUpdateTime(new Date());
    	disciplinaryActionService.updateById(disciplinaryAction);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 单个删除
     *
     * @param
     * @return
     */
    @PostMapping("/delDisciplinaryActionById")
    @ResponseBody
    public UcenterResult delDisciplinaryAction(@RequestParam("disciplinaryActionId") Integer disciplinaryActionId) {
    	disciplinaryActionService.deleteById(disciplinaryActionId);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 批量删除
     *
     * @param
     * @return
     */
    @PostMapping("/delDisciplinaryActions")
    @ResponseBody
    public UcenterResult delDisciplinaryActions(@RequestParam("disciplinaryActionIds") String disciplinaryActionIds) {
        String[] disciplinaryActionIdList = disciplinaryActionIds.split(",");
        disciplinaryActionService.deleteBatchIds(Arrays.asList(disciplinaryActionIdList));
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转查看页面
     *
     * @param
     * @return
     */
    @GetMapping("/lookDisciplinaryAction")
    public ModelAndView lookVisitor(@RequestParam("disciplinaryActionId") Integer disciplinaryActionId) {
        ModelAndView mav = new ModelAndView("disciplinaryAction/lookDisciplinaryAction");
        DisciplinaryAction disciplinaryAction = disciplinaryActionService.selectById(disciplinaryActionId);
        mav.addObject("disciplinaryAction", disciplinaryAction);
        return mav;
    }

}


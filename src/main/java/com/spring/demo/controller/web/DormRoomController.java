package com.spring.demo.controller.web;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.spring.demo.constant.UcenterResult;
import com.spring.demo.constant.UcenterResultConstant;
import com.spring.demo.entity.DormRoom;
import com.spring.demo.entity.DormRoom;
import com.spring.demo.service.DormRoomService;
import com.spring.demo.util.PageDataResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tangxiaoping123
 * @since 2019-05-07
 */
@Controller
@RequestMapping("/dormRoom")
public class DormRoomController {

    @Autowired
    private DormRoomService dormRoomService;

    /**
     * 获得所有宿舍
     *
     * @return
     */
    @GetMapping("getDormRoomList")
    @ResponseBody
    public UcenterResult getDormRoomList() {
        List<DormRoom> dormRoomList = dormRoomService.selectList(new EntityWrapper<DormRoom>());
        return new UcenterResult(UcenterResultConstant.SUCCESS, dormRoomList);
    }
    /**
     * 跳转列表首页
     *
     * @return
     */
    @GetMapping("/dormRoomList")
    public String index() {
        return "dormRoom/dormRoomList";
    }

    /**
     * layui分页查询
     *
     * @param page
     * @param limit
     * @param
     * @return
     */
    @GetMapping("/getPageDormRoomList")
    @ResponseBody
    public PageDataResult getPageDormRoomList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                         @RequestParam(value = "dormRoomNo", required = false) String dormRoomNo) {
        PageDataResult pdr = null;
        pdr = dormRoomService.getPageDormRoomList(page, limit, dormRoomNo);
        return pdr;
    }

    /**
     * 跳转增加页面
     *
     * @return
     */
    @GetMapping("/toAddDormRoom")
    public String toAddVisitor() {
        return "dormRoom/addDormRoom";
    }

    /**
     * 添加visitor
     *
     * @param
     * @return
     */
    @PostMapping("/addDormRoom")
    @ResponseBody
    public UcenterResult addVisitor(@Validated DormRoom dormRoom) {
    	dormRoom.setDromRoomStatus(1);
    	dormRoom.setCreateTime(new Date());
    	dormRoomService.insert(dormRoom);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转修改页面
     *
     * @param
     * @return
     */
    @GetMapping("/toEditDormRoom")
    public ModelAndView toEditVisitor(@RequestParam("dormRoomId") Integer dormRoomId) {
        ModelAndView mav = new ModelAndView("dormRoom/editDormRoom");
        DormRoom dormRoom = dormRoomService.selectById(dormRoomId);
        mav.addObject("dormRoom", dormRoom);
        return mav;
    }

    /**
     * 修改visitor
     *
     * @param
     * @return
     */
    @PostMapping("/editDormRoom")
    @ResponseBody
    public UcenterResult editVisitor(@Validated DormRoom dormRoom) {
    	dormRoom.setUpdateTime(new Date());
    	dormRoomService.updateById(dormRoom);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 单个删除
     *
     * @param
     * @return
     */
    @PostMapping("/delDormRoomById")
    @ResponseBody
    public UcenterResult delDormRoom(@RequestParam("dormRoomId") Integer dormRoomId) {
    	dormRoomService.deleteById(dormRoomId);
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 批量删除
     *
     * @param
     * @return
     */
    @PostMapping("/delDormRooms")
    @ResponseBody
    public UcenterResult delDormRooms(@RequestParam("dormRoomIds") String dormRoomIds) {
        String[] dormRoomIdList = dormRoomIds.split(",");
        dormRoomService.deleteBatchIds(Arrays.asList(dormRoomIdList));
        return new UcenterResult(UcenterResultConstant.SUCCESS, null);
    }

    /**
     * 跳转查看页面
     *
     * @param
     * @return
     */
    @GetMapping("/lookDormRoom")
    public ModelAndView lookVisitor(@RequestParam("dormRoomId") Integer dormRoomId) {
        ModelAndView mav = new ModelAndView("dormRoom/lookDormRoom");
        DormRoom dormRoom = dormRoomService.selectById(dormRoomId);
        mav.addObject("dormRoom", dormRoom);
        return mav;
    }
}


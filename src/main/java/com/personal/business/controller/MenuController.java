/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.business.controller;

import com.alibaba.fastjson.JSON;
import com.personal.business.base.BaseController;
import com.personal.business.base.Return;
import com.personal.business.dto.MenuTree;
import com.personal.business.entity.BtMenu;
import com.personal.business.entity.BtUser;
import com.personal.business.service.IBtMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sunpeikai
 * @version MenuController, v0.1 2019/7/17 10:15
 * @description
 */
@Slf4j
@Controller
@RequestMapping(value = "/system/menu")
public class MenuController extends BaseController {

    @Autowired
    private IBtMenuService iBtMenuService;

    /**
     * @description 进入菜单管理视图
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/init")
    public String init(){
        return "iframe/menuManager";
    }

    /**
     * @description 根据登录用户id获取所有菜单
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/getMenus")
    @ResponseBody
    public Return getMenus(){
        Integer userId = getCurrentUserId();
        log.info("get user menus , userId:[{}]",userId);
        return Return.data(iBtMenuService.getUserMenus(userId));
    }

    /**
     * @description 获取所有菜单
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/getAllMenus")
    @ResponseBody
    public Return getAllMenus(){
        log.info("get all menus");
        return Return.data(iBtMenuService.getAllMenus());
    }

    /**
     * @description 更新菜单
     * @auth sunpeikai
     * @param
     * @return
     */
    @PostMapping(value = "/update")
    @ResponseBody
    public Return update(@RequestBody BtMenu menu){
        log.info(JSON.toJSONString(menu));
        boolean success = iBtMenuService.updateById(menu);
        if(success){
            return Return.success();
        }else{
            return Return.fail("更新失败");
        }
    }

    /**
     * @description 删除菜单
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/delete/{menuId}")
    @ResponseBody
    public Return delete(@PathVariable Integer menuId){
        log.info("delete menu [{}]",menuId);
        boolean success = iBtMenuService.deleteMenus(menuId);
        if(success){
            return Return.success();
        }else{
            return Return.fail("删除失败");
        }
    }

    /**
     * @description 插入菜单
     * @auth sunpeikai
     * @param
     * @return
     */
    @PostMapping(value = "/insert")
    @ResponseBody
    public Return delete(@RequestBody BtMenu menu){
        log.info("insert menu [{}]",JSON.toJSONString(menu));
        BtUser user = getCurrentUser();
        menu.setDelFlag(0);
        menu.setCreateBy(user.getUserName());
        menu.setCreateTime(LocalDateTime.now());
        menu.setUpdateBy(user.getUserName());
        menu.setUpdateTime(LocalDateTime.now());
        boolean success = iBtMenuService.save(menu);
        if(success){
            return Return.success();
        }else{
            return Return.fail("删除失败");
        }
    }

    /**
     * @description 获取用户未授权菜单 - 菜单授权
     * @auth sunpeikai
     * @param
     * @return
     */
    @GetMapping(value = "/getUnAuthorizeMenus/{userId}")
    @ResponseBody
    public Return getUnAuthorizeMenus(@PathVariable Integer userId){
        if(userId!=null){
            List<MenuTree> menus = iBtMenuService.getUnAuthorizeMenus(userId);
            return Return.data(menus);
        }
        return Return.fail("参数缺失[userId]");
    }

    @GetMapping(value = "/getById/{menuId}")
    @ResponseBody
    public Return getById(@PathVariable Integer menuId){
        log.info("get menu by id[{}]",menuId);
        return Return.data(iBtMenuService.getById(menuId));
    }
}

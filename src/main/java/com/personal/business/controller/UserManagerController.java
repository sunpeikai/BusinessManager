/*
 * @Copyright: 2005-2018 www.hyjf.com. All rights reserved.
 */
package com.personal.business.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.personal.business.base.BaseController;
import com.personal.business.base.Return;
import com.personal.business.dto.UserDto;
import com.personal.business.entity.BtUser;
import com.personal.business.request.UserRequest;
import com.personal.business.service.IBtUserService;
import com.personal.business.utils.CommonUtils;
import com.personal.business.utils.DataMaskUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author sunpeikai
 * @version UserManagerController, v0.1 2019/7/22 14:53
 * @description
 */
@Slf4j
@Controller
@RequestMapping(value = "/user")
public class UserManagerController extends BaseController {

    @Autowired
    private IBtUserService iBtUserService;

    @GetMapping(value = "/init")
    public String init(){
        return "iframe/userManager";
    }

    @PostMapping(value = "/searchList")
    @ResponseBody
    public Return searchList(@RequestBody UserRequest userRequest){
        log.info("search user[{}]", JSON.toJSONString(userRequest));
        IPage<BtUser> users = iBtUserService.getAllUsers(userRequest);
        // 实体转换
        IPage<UserDto> result = users.convert(btUser -> {
            UserDto userDto = CommonUtils.convertBean(btUser,UserDto.class);
            // 数据脱敏是否需要做
/*            userDto.setEmail(DataMaskUtils.email(userDto.getEmail()));
            userDto.setMobile(DataMaskUtils.mobile(userDto.getMobile()));*/
            return userDto;
        });
        return Return.data(result);
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public Return update(@RequestBody BtUser user){
        log.info(JSON.toJSONString(user));
        boolean success = iBtUserService.updateById(user);
        if(success){
            return Return.success();
        }else{
            return Return.fail("更新失败");
        }
    }
}

package com.dm.controller;

import com.dm.api.UserFeignApi;
import com.dm.api.UserInfoFeignApi;
import com.dm.bo.RegisterBo;
import com.dm.entity.CommonResult;
import com.dm.exception.BusinessException;
import com.dm.po.User;
import com.dm.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
  *                  ,;,,;
  *                ,;;'(    
  *      __      ,;;' ' \   
  *   /'  '\'~~'~' \ /'\.)  
  * ,;(      )    /  |.     
  *,;' \    /-.,,(   ) \    
  *     ) /       ) / )|    
  *     ||        ||  \)     
  *    (_\       (_\
  *@className UserController
  *@cescription TODO
  *@author dm
  *@date 2021/6/1 17:09
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@RestController
@RequestMapping("/user")
public class UserInfoController implements UserInfoFeignApi {

    @Resource
    private UserService userService;

    @Override
    public CommonResult<User> getInfo(String id) throws BusinessException {
        return CommonResult.success(userService.queryById(id));
    }
}

package com.dm.controller;

import com.dm.api.UserFeignApi;
import com.dm.base.CommonResult;
import com.dm.entity.TokenInfo;
import com.dm.entity.bo.RegisterBo;
import com.dm.entity.po.User;
import com.dm.exception.BusinessException;
import com.dm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "用户管理", tags = "UserController")
@RestController
@RequestMapping("/user")
public class UserController implements UserFeignApi {

    @Resource
    private UserService userService;

    @ApiOperation("注册")
    @Override
    public CommonResult<User> register(RegisterBo registerBo) throws BusinessException {
        return userService.register(registerBo);
    }

    @ApiOperation("验证码生成")
    @Override
    public CommonResult<String> generateAuthCode(String telephone) throws BusinessException {
        return userService.generateAuthCode(telephone);
    }

    @ApiOperation("登录")
    @Override
    public CommonResult<TokenInfo> login(User user) throws BusinessException {
        return CommonResult.success(userService.login(user));
    }

    @ApiOperation("Token刷新")
    @Override
    public String refreshToken(String token) throws BusinessException {
        return userService.refreshToken(token);
    }

}

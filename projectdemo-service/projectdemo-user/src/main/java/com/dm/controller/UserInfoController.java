package com.dm.controller;

import com.dm.api.UserInfoFeignApi;
import com.dm.base.CommonResult;
import com.dm.dao.entity.UserEntity;
import com.dm.entity.po.User;
import com.dm.exception.BusinessException;
import com.dm.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ,;,,;
 * ,;;'(
 * __      ,;;' ' \
 * /'  '\'~~'~' \ /'\.)
 * ,;(      )    /  |.
 * ,;' \    /-.,,(   ) \
 * ) /       ) / )|
 * ||        ||  \)
 * (_\       (_\
 *
 * @author dm
 * @version 1.0
 * @className UserController
 * @cescription TODO
 * @date 2021/6/1 17:09
 * @slogan: 我自横刀向天笑，笑完我就去睡觉
 **/
@Api(value = "用户管理", tags = "UserController")
@RestController
@RequestMapping("/user")
public class UserInfoController implements UserInfoFeignApi {

    @Resource
    private UserService userService;

    @Override
    public CommonResult<User> getInfo(String id) throws BusinessException {
        UserEntity userEntity = userService.queryById(id);

        return CommonResult.success(User.builder()
                .Id(userEntity.getId())
                .nickname(userEntity.getNickname())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .status(userEntity.getStatus()).build());
    }

    @Override
    public CommonResult<User> getInfoByName(String name) throws BusinessException {
        return CommonResult.success(userService.getInfoByName(name));
    }
}

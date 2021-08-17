package com.dm.service;

import com.dm.base.BaseService;
import com.dm.base.CommonResult;
import com.dm.dao.entity.UserEntity;
import com.dm.entity.TokenInfo;
import com.dm.entity.bo.RegisterBo;
import com.dm.entity.po.User;
import com.dm.exception.BusinessException;

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
  *@className UserService
  *@cescription 用户服务接口
  *@author dm
  *@date 2021/5/31 14:14
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
public interface UserService extends BaseService<UserEntity> {

    /**
     * 登录
     *
     * @param user 用户
     */
    TokenInfo login(User user) throws BusinessException;

    /**
     * 刷新token
     *
     * @param token
     */
    String refreshToken(String token) throws BusinessException;

    /**
     * 生成验证码
     *
     * @param telephone 手机号
     */
    CommonResult<String> generateAuthCode(String telephone) throws BusinessException;

    /**
     * 注册
     *
     * @param registerBo 注册实体类
     */
    CommonResult<User> register(RegisterBo registerBo) throws BusinessException;


    User getInfoByName(String name);
}

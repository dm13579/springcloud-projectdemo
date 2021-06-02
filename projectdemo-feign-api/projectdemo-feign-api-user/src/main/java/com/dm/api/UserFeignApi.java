package com.dm.api;

import com.dm.bo.RegisterBo;
import com.dm.entity.CommonResult;
import com.dm.entity.TokenInfo;
import com.dm.exception.BusinessException;
import com.dm.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
  *@className UserApi
  *@cescription UserFeignAPI
  *@author dm
  *@date 2021/6/1 16:21
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@FeignClient(name = "user-service",path = "/user")
@ResponseBody
public interface UserFeignApi {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    CommonResult<User> register(@RequestBody RegisterBo registerBo) throws BusinessException;

    @RequestMapping(value = "/generateAuthCode", method = RequestMethod.GET)
    CommonResult<String> generateAuthCode(@RequestParam("telephone") String telephone) throws BusinessException;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    CommonResult<TokenInfo> login(@RequestBody User user) throws BusinessException;

    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    String refreshToken(@RequestParam("token") String token) throws BusinessException;

}

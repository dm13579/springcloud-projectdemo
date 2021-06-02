package com.dm.api;

import com.dm.bo.RegisterBo;
import com.dm.entity.CommonResult;
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
@FeignClient(name = "user-service",path = "/userinfo")
@ResponseBody
public interface UserInfoFeignApi {

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET)
    CommonResult<User> getInfo(@RequestParam("id") String id) throws BusinessException;
}

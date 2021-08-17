package com.dm.service;

import com.dm.api.UserInfoFeignApi;
import com.dm.base.CommonResult;
import com.dm.entity.po.User;
import com.dm.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Optional;

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
  *@className MyUserDetailService
  *@cescription TODO
  *@author dm
  *@date 2021/5/19 14:49
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserInfoFeignApi userInfoFeignApi;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            log.warn("用户登陆用户名为空:{}", username);
            throw new UsernameNotFoundException("用户名不能为空");
        }
        CommonResult<User> commonResult = new CommonResult<>();
        try {
            commonResult = userInfoFeignApi.getInfoByName(username);
        }catch (BusinessException e){
            log.warn("远程调用,业务异常:{}", commonResult.getMessage());
            throw new UsernameNotFoundException("业务异常");
        }
        User user = commonResult.getData();
        if (!Optional.ofNullable(user).isPresent()) {
            log.warn("根据用户名没有查询到对应的用户信息:{}", username);
            throw new UsernameNotFoundException("用户不存在");
        }
        log.info("根据用户名:{}获取用户登陆信息:{}", username, user);

        return new MyUserDetailsEntity(user);
    }
}

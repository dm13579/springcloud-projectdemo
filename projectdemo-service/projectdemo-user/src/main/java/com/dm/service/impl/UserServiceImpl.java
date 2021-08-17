package com.dm.service.impl;

import com.dm.base.BaseServiceImpl;
import com.dm.base.CommonResult;
import com.dm.constants.UserConstants;
import com.dm.dao.entity.UserEntity;
import com.dm.entity.TokenInfo;
import com.dm.entity.bo.RegisterBo;
import com.dm.entity.po.User;
import com.dm.exception.BusinessException;
import com.dm.dao.mapper.UserMapper;
import com.dm.service.RedisService;
import com.dm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

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
  *@className UserServiceImpl
  *@cescription 用户服务实现类
  *@author dm
  *@date 2021/5/31 14:14
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<UserMapper, UserEntity> implements UserService {

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RedisService redisService;

    @Override
    public TokenInfo login(User user) throws BusinessException {
        ResponseEntity<TokenInfo> response;

        try {
            // 远程调用认证服务器 进行用户登陆
            response = restTemplate.exchange(UserConstants.OAUTH_LOGIN_URL, HttpMethod.POST, wrapOauthTokenRequest(user.getName(), user.getPassword()), TokenInfo.class);
            TokenInfo tokenInfo = response.getBody();
            log.info("根据用户名:{}登陆成功:TokenInfo:{}", user.getName(), tokenInfo);
            return tokenInfo;
        } catch (Exception e) {
            log.error("根据用户名:{}登陆异常:{}", user.getName(), e.getMessage());
            throw new BusinessException("登陆异常");
        }
    }

    @Override
    public String refreshToken(String token) throws BusinessException{
        log.info("RefreshToken的值为:{}", token);

        if (StringUtils.isEmpty(token)) {
            log.error("刷新令牌不能为空:");
            throw new BusinessException("刷新令牌不能为空");
        }

        ResponseEntity<TokenInfo> responseEntity;
        String jwtTokenValue = null;
        try {
            jwtTokenValue = token.substring(UserConstants.TOKEN_HEAD.length());
            //刷新令牌
            responseEntity = restTemplate.exchange(UserConstants.OAUTH_LOGIN_URL, HttpMethod.POST, wrapRefreshTokenRequest(jwtTokenValue), TokenInfo.class);
            TokenInfo tokenInfo = responseEntity.getBody();
            if (tokenInfo == null) {
                log.error("未获取到token");
                throw new BusinessException("未获取到token");
            }
            String newAccessToken = tokenInfo.getAccess_token();
            log.info("通过RefreshToken:{}刷新令牌成功accessToken:{}", jwtTokenValue, newAccessToken);
            return newAccessToken;
        } catch (Exception e) {
            log.error("通过RefreshToken:{}刷新令牌失败:{}", jwtTokenValue, e.getMessage());
            throw new BusinessException("刷新令牌失败");
        }
    }

    @Override
    public CommonResult<String> generateAuthCode(String telephone) throws BusinessException{
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < UserConstants.AUTH_CODE_LENGTH; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.put(UserConstants.REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString(),UserConstants.AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(), "获取验证码成功");
    }

    @Override
    public CommonResult<User> register(RegisterBo registerBean) throws BusinessException {
        // 1,验证验证码
        if (!verifyAuthCode(registerBean.getPhone(),registerBean.getAuthCode())) {
            throw new BusinessException("验证码错误");
        }
        // 2,检验用户是否存在（这里是用户名和手机号联合，也就是说一个手机号可重复注册的）
        List<UserEntity> userList = mapper.select(UserEntity.builder().name(registerBean.getName()).phone(registerBean.getPhone()).build());
        if (!CollectionUtils.isEmpty(userList)) {
            throw new BusinessException("用户已存在不可重复注册");
        }
        // 3,检验基本信息

        // 4,创建用户
        UserEntity userEntity = UserEntity.builder()
                .name(registerBean.getName())
                .phone(registerBean.getPhone())
                .build();
        mapper.insertSelective(userEntity);


        return CommonResult.success(User.builder()
                .name(registerBean.getName())
                .phone(registerBean.getPhone())
                .build());
    }

    @Override
    public User getInfoByName(String name) {
        UserEntity userEntity = mapper.selectOne(UserEntity.builder().name(name).build());
        return User.builder()
                .Id(userEntity.getId())
                .nickname(userEntity.getNickname())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .status(userEntity.getStatus()).build();
    }

    /**
     * 对输入的验证码进行校验
     *
     * @param authCode  校驗碼
     * @param telephone 號碼
     * @return boolean
     */
    private boolean verifyAuthCode(String authCode, String telephone) {
        if (StringUtils.isEmpty(authCode)) {
            return false;
        }
        String realAuthCode = (String) redisService.get(UserConstants.REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return authCode.equals(realAuthCode);
    }

    /**
     * 封装请求参数
     *
     * @param username 用户名
     * @param password 密码
     */
    private HttpEntity<MultiValueMap<String, String>> wrapOauthTokenRequest(String username, String password) {

        //封装oauth2 请求头 clientId clientSecret
        HttpHeaders httpHeaders = wrapHttpHeaders();

        //封装请求参数
        MultiValueMap<String, String> reqParams = new LinkedMultiValueMap<>();
        reqParams.add(UserConstants.USER_NAME, username);
        reqParams.add(UserConstants.PASS, password);
        reqParams.add(UserConstants.GRANT_TYPE, UserConstants.PASS);
        reqParams.add(UserConstants.SCOPE, UserConstants.SCOPE_AUTH);

        return new HttpEntity<>(reqParams, httpHeaders);
    }

    /**
     * 封装刷新token的请求
     *
     * @param refreshToken token
     */
    private HttpEntity<MultiValueMap<String, String>> wrapRefreshTokenRequest(String refreshToken) {

        HttpHeaders httpHeaders = wrapHttpHeaders();

        MultiValueMap<String, String> param = new LinkedMultiValueMap<>();
        param.add("grant_type", UserConstants.REFRESH_TOKEN_GRANT_TYPE);
        param.add("refresh_token", refreshToken);

        return new HttpEntity<>(param, httpHeaders);
    }

    /**
     * 封装请求头，包含OAuth2用户名密码(oauth_client_details)
     */
    private HttpHeaders wrapHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(UserConstants.CLIENT_ID, UserConstants.CLIENT_SECRET);
        return httpHeaders;
    }
}

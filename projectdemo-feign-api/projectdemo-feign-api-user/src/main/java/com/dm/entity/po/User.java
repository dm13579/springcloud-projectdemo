package com.dm.entity.po;

import com.dm.enums.GenderType;
import com.dm.enums.AccountStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

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
  *@className User
  *@cescription TODO
  *@author dm
  *@date 2021/5/19 14:23
  *@slogan: 我自横刀向天笑，笑完我就去睡觉
  *@version 1.0
  **/
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    /**
     * 序列化版本编号
     */
    private static final long serialVersionUID = -7457106252004982786L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键")
    public String Id;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "性别：0->未知；1->男；2->女")
    private GenderType gender;

    @ApiModelProperty(value = "帐号启用状态:0->禁用；1->启用")
    private AccountStatus status;

    @ApiModelProperty(value = "注册时间")
    private Date createTime;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
}

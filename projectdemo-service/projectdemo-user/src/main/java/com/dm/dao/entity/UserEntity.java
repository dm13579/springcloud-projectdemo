package com.dm.dao.entity;

import com.dm.base.BaseEntity;
import com.dm.enums.AccountStatus;
import com.dm.enums.GenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
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
public class UserEntity extends BaseEntity {

    /**
     * 序列化版本编号
     */
    private static final long serialVersionUID = -7457106252004982786L;

    private String name;

    private String phone;

    private String password;

    private String nickname;

    private String avatar;

    @Column(name = "gender")
    private GenderType gender;

    @Column(name = "status")
    private AccountStatus status;

    private Date createTime;

    private Date updateTime;
}
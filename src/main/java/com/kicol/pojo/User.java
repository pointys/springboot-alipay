package com.kicol.pojo;

import lombok.Data;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/23 0023 15:07
 */
@Data
public class User {
    private Integer userId;
    private Integer roleId;
    private String userName;
    private String userSex;
    private Integer userAge;
    private String userPhone;
    private String userAccount;
    private String userPwd;
    private Float userSalary;
    private String userMark;
}

package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录成功结果封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo {
    private Integer id;            // 用户的 ID
    private String username;       // 用户名
    private String password;       // 密码
    private String role;           // 角色
    private String token;          // JWT令牌
    private Integer employeeId;    // 员工ID
}


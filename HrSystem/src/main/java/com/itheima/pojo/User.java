package com.itheima.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private String token; //令牌
    private String avatarUrl;  // 添加 avatarUrl 字段
    private Boolean isActive;   // 添加 isActive 字段
}

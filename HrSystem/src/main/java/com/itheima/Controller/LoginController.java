package com.itheima.Controller;

import com.itheima.Service.UserService;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@CrossOrigin(origins = "*")  // 允许所有来源的跨域请求
public class LoginController {
@Autowired
private UserService UserService;
@PostMapping("/login")
    public Result login(@RequestBody User user){
        LoginInfo loginInfo = UserService.login(user);
        if (loginInfo != null) {
            return Result.success(loginInfo);
        } else {
            return Result.error("用户名或密码错误");
        }

    }

}

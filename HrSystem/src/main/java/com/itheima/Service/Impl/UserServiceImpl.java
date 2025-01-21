package com.itheima.Service.Impl;

import com.itheima.Mapper.EmployeeMapper;
import com.itheima.Mapper.UserLogMapper;
import com.itheima.Service.UserService;
import com.itheima.Util.JwtUtils;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserLogMapper UserLogMapper;

    @Autowired
    private EmployeeMapper employeeMapper; // 需要通过 employeeMapper 来查询 employeeId
    @Override
    public LoginInfo login(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            log.warn("登录尝试时，提供了无效的凭证：用户名或密码为空");
            return null;  // 如果输入无效，返回null
        }

        // 查询用户
        User userLogin = UserLogMapper.getUserLogin(user);
        if (userLogin != null) {
            // 获取对应的 employeeId
            Integer employeeId = employeeMapper.getEmployeeIdByUserId(userLogin.getId());

            // 生成JWT令牌
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("id", userLogin.getId());
            dataMap.put("username", userLogin.getUsername());
            dataMap.put("role", userLogin.getRole());  // 将角色添加到数据中
            dataMap.put("employeeId", employeeId);    // 将 employeeId 也返回

            String jwt = JwtUtils.generateToken(dataMap);

            LoginInfo loginInfo = new LoginInfo(userLogin.getId(), userLogin.getUsername(), userLogin.getPassword(),
                    userLogin.getRole(), jwt, employeeId); // 添加 employeeId 到 LoginInfo 中

            return loginInfo;
        }

        // 如果用户登录失败，记录日志并返回null
        log.warn("用户 {} 登录失败，用户名或密码错误", user.getUsername());
        return null;
    }

}

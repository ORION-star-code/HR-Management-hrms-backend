package com.itheima.Service;

import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

      LoginInfo login(User user);
}

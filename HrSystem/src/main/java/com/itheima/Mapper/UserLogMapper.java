package com.itheima.Mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserLogMapper {

    @Select("select * from users where username=#{username} and password=#{password}")
    User getUserLogin(User user);
}

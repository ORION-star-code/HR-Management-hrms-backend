package com.itheima.Mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    // 插入用户数据
    @Insert("INSERT INTO users (username, password, role, avatar_url, is_active) " +
            "VALUES (#{username}, #{password}, #{role}, #{avatarUrl}, #{isActive})")
    @Options(useGeneratedKeys = true, keyProperty = "id")  // 获取自动生成的 id
    int insert(User user);

    @Select("SELECT id FROM users WHERE username = #{username}")
    Long getUserIdByUsername(String username);
    // 通过 userId 查找用户
    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectById(@Param("id") int id);

    // 更新用户信息（这里只是更新用户名，你可以根据实际需要更新其他字段）
    @Update("UPDATE users SET username = #{username}, password = #{password}, role = #{role}, avatar_url = #{avatarUrl}, is_active = #{isActive} WHERE id = #{id}")
    int update(User user);
}

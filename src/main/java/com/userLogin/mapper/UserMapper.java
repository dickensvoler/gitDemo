package com.userLogin.mapper;

import com.userLogin.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Select("select * from tb_user where username=#{username} and password=${password}")
    User Select(@Param("username") String username, @Param("password") String password);

    /**
     * 查询用户是否存在
     *
     * @param username
     * @return
     */
    @Select("select * from tb_user where username=#{username}")
    User SelectByUsername(@Param("username") String username);

    /**
     * 新增用户
     *
     * @param user
     */
    @Insert("insert into tb_user values (null, #{username}, #{password})")
    void Insert(User user);
}

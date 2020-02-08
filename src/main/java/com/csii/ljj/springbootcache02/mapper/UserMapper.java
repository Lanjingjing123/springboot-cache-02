package com.csii.ljj.springbootcache02.mapper;

import com.csii.ljj.springbootcache02.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

// 指定这是一个
@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    public User selectById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into user(name) values(#{name})")
    public int insertByUser(User user);
}

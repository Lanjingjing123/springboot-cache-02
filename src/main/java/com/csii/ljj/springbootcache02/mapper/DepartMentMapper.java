package com.csii.ljj.springbootcache02.mapper;

import com.csii.ljj.springbootcache02.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartMentMapper {
    @Select("SELECT * FROM department WHERE id=#{id}")
    public Department selectById(Integer id);
}

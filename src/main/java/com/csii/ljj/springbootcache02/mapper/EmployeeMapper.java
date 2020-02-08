package com.csii.ljj.springbootcache02.mapper;

import com.csii.ljj.springbootcache02.bean.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@Mapper
public interface EmployeeMapper {
    @Select("SELECT * FROM employee where id=#{id}")
    public Employee selectById(Integer id);


    public int insertByEmployee(Employee employee);


    @Update("UPDATE employee SET employee_Name=#{employeeName} WHERE id=#{id}")
    public int updateEmployee(Employee employee);

    @Delete("DELETE FROM employee WHERE id=#{id}")
    public void deleteEmployee(Integer id);

    @Select("SELECT * FROM employee where employee_Name=#{employeeName}")
    public Employee selectByEmployeeName(String employeeName);
}

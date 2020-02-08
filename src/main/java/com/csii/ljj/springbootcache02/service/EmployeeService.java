package com.csii.ljj.springbootcache02.service;

import com.csii.ljj.springbootcache02.bean.Employee;
import com.csii.ljj.springbootcache02.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmployeeService {
    @Resource
    EmployeeMapper employeeMapper;

    @Qualifier("employeeCacheManager")
    @Autowired
    RedisCacheManager employeeCacheManager;

    /**
     * 将方法的结果进行缓存
     *
     * CacheManager:管理多个Cache组件的，对缓存的真正的crud是Cache,这里指定自定义的employeeCacheManager
     * 几个属性：cacheNames/value:指定缓存的名字
     * key:缓存数据使用的key；可以用它来指定，默认是使用方法参数的值，
     * keyGenerator:生成器
     *      key/keyGenerator,2选1
     * cacheManager:
     * condition:指定符合条件的情况下缓存;condition = "#{id}>0"
     * unless:否定缓存；当unless指定的条件为true，则不进行缓存,unless = "#result==null"
     * sync:是否使用异步
     * @param id
     * @return
     */
    @Cacheable(cacheManager = "employeeCacheManager",cacheNames = "emp",key = "#root.args[0]")
    public Employee getEmp(Integer id){
        System.out.println("query>>>>>>>>>>>>>>>>>>>>>>>>>");
        Employee employee= employeeMapper.selectById(id);
        return employee;
    }


    /**
     * 将结果进行缓存，key为入参；需要和查询的key指定为同一个
     * @param employee
     * @return
     */
    @CachePut(cacheManager = "employeeCacheManager",cacheNames = "emp",key = "#result.id")
    public Employee updateEmp(Employee employee){
        Integer uddate = employeeMapper.updateEmployee(employee);
        System.out.println("==========================update= "+uddate);
        return  employeeMapper.selectById(employee.getId());
    }

    /**
     * allEntries：是否清除当前cacheNames = "emp"的所有缓存
     * beforeInvocation:指定是否在方法之前执行，默认为方法之后执行
     *
     * @param id
     */
    @CacheEvict(cacheManager = "employeeCacheManager",cacheNames = "emp",key = "#id"/*allEntries = true*/,beforeInvocation = true)
    public void deleteEmp(Integer id){
//        employeeMapper.deleteEmployee(id);
        System.out.println("开始清除缓存》》》》》》》》》》》》》》》》》》");
    }


    @Caching(
            cacheable={
                    @Cacheable(value = "emp",key = "#employee.employeeName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.phoneNo")
            }
    )
    public Employee queryEmpByName(Employee employee){

        Employee employee2 = employeeMapper.selectByEmployeeName(employee.getEmployeeName());
        return employee2;
    }
}

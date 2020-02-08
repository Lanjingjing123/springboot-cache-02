package com.csii.ljj.springbootcache02;

import com.csii.ljj.springbootcache02.bean.Employee;
import com.csii.ljj.springbootcache02.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootCache02ApplicationTests {

    @Resource
    EmployeeMapper employeeMapper;
    /**
     * 操作字符串
     * K,v都是字符串
     */
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 操作对象
     * k,v都是对象
     *
     */
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    @Qualifier("empRedisTemplate")
    RedisTemplate<Object,Employee> empRedisTemplate;
    /**
     * Redis 常见的五大数据类型
     * String,list,set,hash,zet(有序集合):
     *  stringRedisTemplate.opsForValue()
     *  stringRedisTemplate.opsForHash()
     */
    @Test
    public void testRedis(){
//        stringRedisTemplate.opsForValue().append("msg","hello world");
//        System.out.println(stringRedisTemplate.opsForValue().get("msg"));
//        stringRedisTemplate.opsForList().leftPushAll("myNames","张三","李四","王五");
////
        Employee employee = employeeMapper.selectById(1);
        System.out.println(employee.getEmployeeName());
        // 方法一：Employee 使用jdk自带的序列化保存到redis中
        redisTemplate.opsForValue().set("emp-01",employee);
        System.out.println(redisTemplate.opsForValue().get("emp-01"));

        // 方法二：使用自己配置RedisConfig.java中配置的json序列化器存储
        empRedisTemplate.opsForValue().set("emp-02",employee);
        System.out.println(empRedisTemplate.opsForValue().get("emp-02"));

    }

    @Test
    public void contextLoads() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmployeeName("张三");
        int update = employeeMapper.updateEmployee(employee);

        System.out.println(update);
    }

}

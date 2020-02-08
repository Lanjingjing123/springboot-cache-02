package com.csii.ljj.springbootcache02.config;

import com.csii.ljj.springbootcache02.bean.Department;
import com.csii.ljj.springbootcache02.bean.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.net.UnknownHostException;

@Configuration
public class RedisConfig {
    @Bean("empRedisTemplate")
    public RedisTemplate<Object, Employee> redisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Employee> template = new RedisTemplate<Object, Employee>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    @Bean
    @Primary
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object,Employee> employeeRedisTemplate){
        // 自定义cacheManager，序列化以json格式存储
        RedisCacheManager cacheManager = new RedisCacheManager(employeeRedisTemplate);
        // 默认使用前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }





    @Bean("deptRedisTemplate")
    public RedisTemplate<Object, Department> deptRedisTemplate(
            RedisConnectionFactory redisConnectionFactory)
            throws UnknownHostException {
        RedisTemplate<Object, Department> template = new RedisTemplate<Object, Department>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Department> serializer = new Jackson2JsonRedisSerializer<Department>(Department.class);
        template.setDefaultSerializer(serializer);
        return template;
    }

    @Bean("deptCacheManager")
    public RedisCacheManager deptCacheManager(RedisTemplate<Object,Department> employeeRedisTemplate){
        // 自定义cacheManager，序列化以json格式存储
        RedisCacheManager cacheManager = new RedisCacheManager(employeeRedisTemplate);
        // 默认使用前缀
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }
}

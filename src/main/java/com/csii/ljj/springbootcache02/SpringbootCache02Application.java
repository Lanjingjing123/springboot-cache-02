package com.csii.ljj.springbootcache02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 原理：CacheManager====Cache 缓存组件来实际给缓存中存取数据
 * 1).引入redis的starter，容器中保存的是redisCacheManager
 * 2）.RedisCacheManager 帮我们创建RedisCache来作为缓存组件，RedisCache通过操作Redis缓存数据
 * 3）.默认保存数据k-v都是Object，利用序列化保存，如何保存json
 *     1.引入redis的starter
 *     RedisTemplate<Object, Object>  默认使用jdk序列化器，defaultSerializer = new JdkSerializationRedisSerializer
 */
@MapperScan("com.csii.ljj.springbootcache02.mapper")
@EnableCaching
@SpringBootApplication
public class SpringbootCache02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCache02Application.class, args);
    }

}

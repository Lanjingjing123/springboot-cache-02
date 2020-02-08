package com.csii.ljj.springbootcache02.service;

import com.csii.ljj.springbootcache02.bean.Department;
import com.csii.ljj.springbootcache02.mapper.DepartMentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service

public class DepartmentService {

    @Resource
    DepartMentMapper departMentMapper;

    @Qualifier("deptCacheManager")
    @Autowired
    RedisCacheManager deptCacheManager;

    public Department selectDept(Integer id){
        Department department = null;

        Cache cache = deptCacheManager.getCache("dept-1");
        // 主动加入缓存
        if (cache.get("dept:1")==null||("").equals(cache.get("dept:1"))){
            department = departMentMapper.selectById(id);
            cache.put("dept:1",department);
        } else{
            department = cache.get("dept:1",Department.class);
        }
        return department;
    }
}

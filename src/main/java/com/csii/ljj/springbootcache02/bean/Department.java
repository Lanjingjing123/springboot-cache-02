package com.csii.ljj.springbootcache02.bean;

import java.io.Serializable;

/**
 * pojo
 */
public class Department implements Serializable {
    private Integer id;
    private String departmentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departName='" + departmentName + '\'' +
                '}';
    }
}

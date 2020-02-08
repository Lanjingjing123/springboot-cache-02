package com.csii.ljj.springbootcache02.bean;

import java.io.Serializable;

public class Employee  implements Serializable {
    private Integer id;
    private String employeeName;
    private String address;
    private Integer phoneNo;
    private Integer dId;

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Integer phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNo=" + phoneNo +
                ", dId=" + dId +
                '}';
    }
}

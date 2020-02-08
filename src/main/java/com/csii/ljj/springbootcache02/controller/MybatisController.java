package com.csii.ljj.springbootcache02.controller;


import com.csii.ljj.springbootcache02.bean.Department;
import com.csii.ljj.springbootcache02.service.DepartmentService;
import com.csii.ljj.springbootcache02.service.EmployeeService;
import com.csii.ljj.springbootcache02.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MybatisController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/employee/{id}")
    public Employee selectById(@PathVariable("id") Integer id){
        return employeeService.getEmp(id);
    }

    @GetMapping("/employee")
    public Employee updateEmp(Employee employee){
        Employee employee1 = employeeService.updateEmp(employee);
        return employee1;
    }

    @GetMapping("/delemp")
    public String delEMp(Integer id){
        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/employee/employeeName")
    public Employee selectByEmployeeName(Employee employee){
        return employeeService.queryEmpByName(employee);
    }

    @GetMapping("/dept/{id}")
    public Department selectDeptById(@PathVariable("id") Integer id){
        return departmentService.selectDept(id);
    }
}

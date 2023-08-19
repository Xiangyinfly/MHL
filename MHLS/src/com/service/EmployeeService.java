package com.service;

import com.dao.EmployeeDao;
import com.domain.Employee;

import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    public Employee getEmp(String empId,String pwd) throws Exception {
        List<Employee> data = employeeDao.executeQuery(Employee.class, "select * from employee where empId=? and passwd=md5(?)", empId, pwd);
        if (!data.isEmpty()) {
            return data.get(0);
        }
        return null;
    }
}

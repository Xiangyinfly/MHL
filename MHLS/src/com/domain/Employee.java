package com.domain;

public class Employee {
    private Integer id;
    private String empId;
    private String passwd;
    private String name;
    private String job;


    //reflection要用到无参构造
    public Employee() {
    }

    public Employee(Integer id, String empId, String passwd, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.passwd = passwd;
        this.name = name;
        this.job = job;
    }
}

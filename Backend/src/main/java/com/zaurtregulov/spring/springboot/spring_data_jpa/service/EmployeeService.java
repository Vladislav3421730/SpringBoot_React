package com.zaurtregulov.spring.springboot.spring_data_jpa.service;


import com.zaurtregulov.spring.springboot.spring_data_jpa.entity.Employee;
import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(Employee employee);

    public Employee getEmployee(int id);
    public void deleteEmployee(int id);

    public List<Employee> findAllByName(String name);

    public List<Employee> findAllBySalaryIsBetween(int min,int max);
}

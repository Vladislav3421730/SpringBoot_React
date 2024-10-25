package com.zaurtregulov.spring.springboot.spring_data_jpa.service;


import com.zaurtregulov.spring.springboot.spring_data_jpa.dao.EmployeeRepository;
import com.zaurtregulov.spring.springboot.spring_data_jpa.entity.Employee;
import com.zaurtregulov.spring.springboot.spring_data_jpa.exception_handling.NoSuchEmployeeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
       return employeeRepository.save(employee);
    }


    public Employee updateEmployee(Employee employee) {
        Optional<Employee> UpdatingEmployee=employeeRepository.findById(employee.getId());
        if(UpdatingEmployee.isEmpty()){
            throw new NoSuchEmployeeException("Employee with id "+employee.getId()+" not found");
        }
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).
                orElseThrow(()->new NoSuchEmployeeException("Employee with id "+id+" not found"));
    }

    public void deleteEmployee(int id) {
      employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllByName(String name) {
        return employeeRepository.findAllByName(name);
    }

    @Override
    public List<Employee> findAllBySalaryIsBetween(int min, int max) {
        return employeeRepository.findAllBySalaryIsBetween(min,max);
    }

}

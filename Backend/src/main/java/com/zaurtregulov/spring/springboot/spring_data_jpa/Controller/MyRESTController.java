package com.zaurtregulov.spring.springboot.spring_data_jpa.Controller;

import com.zaurtregulov.spring.springboot.spring_data_jpa.entity.Employee;
import com.zaurtregulov.spring.springboot.spring_data_jpa.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MyRESTController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/emp")
    public List<Employee> showAllEmployees()
    {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/emp/{id}")
    public Employee GetEmployee(@PathVariable int id)
    {
        return employeeService.getEmployee(id);
    }

    @PostMapping(value="/emp",consumes="application/json",produces="application/json" )
    public Employee addEmployee(@RequestBody @Valid Employee employee)
    {
       return employeeService.saveEmployee(employee);
    }
   @PutMapping(value="/emp",consumes="application/json",produces="application/json" )
    public Employee updateEmployee(@RequestBody @Valid Employee employee)
    {
       return employeeService.updateEmployee(employee);

    }
    @DeleteMapping(value = "/emp/{id}",produces="application/json" )
    public String deleteEmployee(@PathVariable int id)
    {
        employeeService.getEmployee(id);
        employeeService.deleteEmployee(id);
        return "{\"info\": \"Employee with id "+id+" was deleted\"}";
    }
    @GetMapping("/emp/name/{name}")
    public List<Employee> GetAllEmployeeByName(@PathVariable String name)
    {
       return employeeService.findAllByName(name);
    }

    @GetMapping("/emp/salary/{salary1}/{salary2}")
    public List<Employee> GetAllEmployeeBySalaryIsBetween(@PathVariable int salary1,@PathVariable int salary2)
    {
        return employeeService.findAllBySalaryIsBetween(salary1,salary2);
    }

}

package com.zaurtregulov.spring.springboot.spring_data_jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.zaurtregulov.spring.springboot.spring_data_jpa.Controller.MyRESTController;
import com.zaurtregulov.spring.springboot.spring_data_jpa.entity.Employee;
import com.zaurtregulov.spring.springboot.spring_data_jpa.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(MyRESTController.class)
class SpringDataJpaApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;


    ObjectMapper objectMapper=new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    Employee employee1=new Employee(1,"Vlad","Panasik","Department",200);
    Employee employee2=new Employee(2,"Marina","Polkinovna","IT",500);
    Employee employee3=new Employee(3,"Gleb","Lof","Management",900);


    @Test
    public void TestAllEmployees() throws Exception {
        List<Employee> employees=List.of(employee1,employee2,employee3);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/emp"))
                .andExpect(status().isOk())
                .andExpectAll(jsonPath("$",hasSize(3)),
                        jsonPath("$[0].name",is("Vlad")),
                        jsonPath("$[1].surname",is("Polkinovna")),
                        jsonPath("$[2].salary",is(900)));

    }

    @Test
    public void TestGetEmployeeById() throws Exception {
        when(employeeService.getEmployee(employee2.getId())).thenReturn(employee2);

        mockMvc.perform(get("/emp/"+employee2.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpectAll(jsonPath("$.name",is("Marina")),
                        jsonPath("$.surname",is("Polkinovna")),
                        jsonPath("$.department",is("IT")),
                        jsonPath("$.salary",is(500)));
    }

    @Test
    public void TestSaveEmployee() throws Exception{
        Employee employee4=Employee.builder()
                .id(4)
                .name("Mike")
                .surname("Losin")
                .department("Sales")
                .salary(350)
                .build();

        when(employeeService.saveEmployee(employee4)).thenReturn(employee4);

        String content=objectWriter.writeValueAsString(employee4);

        MockHttpServletRequestBuilder mockMvcRequestBuilders = post("/emp")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockMvcRequestBuilders)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpectAll(jsonPath("$.id",is(4)),
                        jsonPath("$.name",is("Mike")),
                        jsonPath("$.surname",is("Losin")),
                        jsonPath("$.department",is("Sales")),
                        jsonPath("$.salary",is(350)));
    }

    @Test
    public void TestUpdateEmployee() throws Exception{
        Employee UpdatingEmployee=Employee.builder()
                .id(1)
                .name("Vladimir")
                .surname("Panasik")
                .department("Sales")
                .salary(200)
                .build();

        when(employeeService.getEmployee(employee1.getId())).thenReturn(employee1);
        when(employeeService.updateEmployee(UpdatingEmployee)).thenReturn(UpdatingEmployee);

        String content=objectWriter.writeValueAsString(UpdatingEmployee);

        MockHttpServletRequestBuilder mockMvcRequestBuilders = put("/emp")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockMvcRequestBuilders)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpectAll(jsonPath("$.id",is(1)),
                        jsonPath("$.name",is("Vladimir")),
                        jsonPath("$.surname",is("Panasik")),
                        jsonPath("$.department",is("Sales")),
                        jsonPath("$.salary",is(200)));
    }

}

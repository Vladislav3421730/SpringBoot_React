package com.zaurtregulov.spring.springboot.spring_data_jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employees")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="name")
    @NotBlank(message = "This field shouldn't be empty")
    private String name;
    @Column(name = "surname")
    @NotBlank(message = "This field shouldn't be empty")
    private String surname;

    @Column(name="department")
    @NotBlank(message = "This field shouldn't be empty")
    private String department;

    @Column(name="salary")
    @Min(value = 100,message = "Salary must be not less than 100")
    private int salary;
}

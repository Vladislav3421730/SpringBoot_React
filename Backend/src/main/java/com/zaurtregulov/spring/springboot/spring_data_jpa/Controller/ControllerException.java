package com.zaurtregulov.spring.springboot.spring_data_jpa.Controller;


import com.zaurtregulov.spring.springboot.spring_data_jpa.exception_handling.EmployeeIncorrectData;
import com.zaurtregulov.spring.springboot.spring_data_jpa.exception_handling.NoSuchEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerException {

    @ExceptionHandler(NoSuchEmployeeException.class)
    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception) {
        EmployeeIncorrectData employeeIncorrectData = new EmployeeIncorrectData();
        employeeIncorrectData.setMessage(exception.getMessage());
        return new ResponseEntity<>(employeeIncorrectData, HttpStatus.NOT_FOUND);
    }
}

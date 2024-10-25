package com.zaurtregulov.spring.springboot.spring_data_jpa.exception_handling;

public class NoSuchEmployeeException extends RuntimeException{

    public NoSuchEmployeeException(String message) {
        super(message);
    }
}

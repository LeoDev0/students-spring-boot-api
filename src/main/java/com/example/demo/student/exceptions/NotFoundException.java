package com.example.demo.student.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static String MESSAGE = "Resource not found.";

    public NotFoundException() {
        super(MESSAGE);
    }

    public NotFoundException(String message) {
        super(message);
    }

}

package com.example.demo.student.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    private static String MESSAGE = "Bad request.";

    public BadRequestException() {
        super(MESSAGE);
    }

    public BadRequestException(String message) {
        super(message);
    }

}

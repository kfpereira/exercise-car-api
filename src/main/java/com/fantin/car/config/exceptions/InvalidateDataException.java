package com.fantin.car.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidateDataException extends Exception {

    public InvalidateDataException(String message) {
        super(message);
    }

}

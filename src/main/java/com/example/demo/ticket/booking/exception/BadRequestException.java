package com.example.demo.ticket.booking.exception;

import java.util.List;
import com.example.demo.ticket.booking.model.error.Error;

public class BadRequestException extends BaseException {

    public BadRequestException(List<Error> errorList) {
        super(errorList);
    }
}

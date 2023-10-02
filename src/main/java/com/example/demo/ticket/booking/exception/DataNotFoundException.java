package com.example.demo.ticket.booking.exception;

import java.util.List;
import com.example.demo.ticket.booking.model.error.Error;

public class DataNotFoundException extends BaseException {

    public DataNotFoundException(List<Error> errorList) {
        super(errorList);
    }
}

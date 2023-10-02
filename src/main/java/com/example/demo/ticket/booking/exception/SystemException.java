package com.example.demo.ticket.booking.exception;

import java.util.List;
import com.example.demo.ticket.booking.model.error.Error;

public class SystemException extends BaseException {

    public SystemException(List<Error> errorList) {
        super(errorList);
    }
}

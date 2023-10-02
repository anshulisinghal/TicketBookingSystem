package com.example.demo.ticket.booking.exception;

import java.util.List;
import com.example.demo.ticket.booking.model.error.Error;

public class BaseException extends RuntimeException {

    private final List<Error> errorList;

    public BaseException(List<Error> errorList) {
        super();
        this.errorList = errorList;
    }

    public BaseException(String field, String errorCode, String errorMsg) {
        super();
        this.errorList = List.of(new Error(field, errorCode, errorMsg));
    }

    public List<Error> getErrorList() {
        return errorList;
    }
}

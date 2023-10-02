package com.example.demo.ticket.booking.exception;

import java.util.List;
import com.example.demo.ticket.booking.model.error.Error;

public class ShowsNotFoundException extends BaseException {

    public ShowsNotFoundException(List<Error> errorList) {
        super(errorList);
    }
}

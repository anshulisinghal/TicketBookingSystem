package com.example.demo.ticket.booking.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Error {

    private String field;
    private String errorCode;
    private String errorMessage;
}

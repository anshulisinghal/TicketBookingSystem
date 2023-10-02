package com.example.demo.ticket.booking.model.error;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

    private T data;
    private List<Error> errorResponse;
}

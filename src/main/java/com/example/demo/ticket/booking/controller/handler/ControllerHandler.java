package com.example.demo.ticket.booking.controller.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.demo.ticket.booking.exception.BadRequestException;
import com.example.demo.ticket.booking.exception.DataNotFoundException;
import com.example.demo.ticket.booking.model.error.ApiResponse;
import com.example.demo.ticket.booking.model.error.Error;

@ControllerAdvice
public class ControllerHandler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Object> handleDataNotFoundException(DataNotFoundException dataNotFoundException, HttpServletRequest request,
        HttpServletResponse response) {
        return createApiResponse(dataNotFoundException.getErrorList());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse<Object> handleBadRequestException(BadRequestException badRequestException, HttpServletRequest request,
        HttpServletResponse response) {
        return createApiResponse(badRequestException.getErrorList());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        List<Error> errorList = errors.entrySet().stream().map(entrySet -> new Error(entrySet.getKey(), "INVALID", entrySet.getValue()))
            .collect(Collectors.toList());
      return createApiResponse(errorList);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Object> handleServerException(Exception ex, HttpServletRequest request,
        HttpServletResponse response) {
        List<Error> errorList = List.of(new Error("", "INTERNAL_SERVER_ERROR", ex.getMessage()));
        return createApiResponse(errorList);
    }

    private ApiResponse<Object> createApiResponse(List<Error> errorList) {

        ApiResponse<Object> apiResponse = new ApiResponse<>();
        apiResponse.setErrorResponse(errorList);
        return apiResponse;

    }
}

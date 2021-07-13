package com.project.critter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerApiRequestException(CustomerNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z"))), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<?> handleCustomerApiRequestException(EmployeeNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z"))), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = PetNotFoundException.class)
    public ResponseEntity<?> handleCustomerApiRequestException(PetNotFoundException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z"))), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<?> handleCustomerApiRequestException(IllegalStateException e) {
        return new ResponseEntity<>(
                new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("Z"))), HttpStatus.BAD_REQUEST);
    }
}

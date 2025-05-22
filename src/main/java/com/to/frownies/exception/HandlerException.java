package com.to.frownies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(CustomExc.class)
    public ResponseEntity<CarrierExc> handle(CustomExc e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CarrierExc(e.getCode(), e.getMessage() + e.getDetail()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CarrierExc> handle(Exception e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new CarrierExc(0, "Forbidden err: " + e.getMessage()));
    }
}

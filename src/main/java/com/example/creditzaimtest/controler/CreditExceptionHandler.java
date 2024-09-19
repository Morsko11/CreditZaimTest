package com.example.creditzaimtest.controler;

import com.example.creditzaimtest.dto.ErrorDto;
import com.example.creditzaimtest.exception.CalculationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class CreditExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(ErrorDto.builder()
                .message("Validation error")
                .description(Optional.ofNullable(e.getBindingResult().getFieldError()).map(error -> error.getField() + " " + error.getDefaultMessage()).orElse("Unknown error"))
                .code(HttpStatus.BAD_REQUEST)
                .build());
    }

    @ExceptionHandler(CalculationException.class)
    public ResponseEntity<ErrorDto> handleCalculationException(CalculationException e) {
        return ResponseEntity.badRequest().body(ErrorDto.builder()
                .message("Calculation error")
                .description(e.getMessage())
                .code(HttpStatus.BAD_REQUEST)
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorDto.builder()
                .message("Internal server error")
                .description(e.getMessage())
                .code(HttpStatus.INTERNAL_SERVER_ERROR)
                .build());
    }
}
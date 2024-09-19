package com.example.creditzaimtest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private String message;
    private String description;
    private HttpStatus code;

}

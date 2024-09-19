package com.example.creditzaimtest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreditDto {
    private Long id;
    @NotBlank
    @Pattern(regexp = "^[A0123]*$")
    private String credit_pmt_string;
    @NotNull
    private LocalDate credit_pmt_start;
}
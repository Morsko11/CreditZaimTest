package com.example.creditzaimtest.controler;

import com.example.creditzaimtest.dto.CreditDto;
import com.example.creditzaimtest.service.CreditService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @GetMapping("/calculate")
    public ResponseEntity<String> calculateAndLog() {
        String result = creditService.calculateCrossLoanPaymentString();
        log.info("Calculation result: {}", result);// Вывод в консоль
        return ResponseEntity.ok(result);  // Возвращаем результат как HTTP ответ
    }

    @PostMapping("/save")
    public ResponseEntity<CreditDto> saveCredit(@RequestBody @Valid CreditDto creditDto) {
        return ResponseEntity.ok(creditService.save(creditDto));
    }
}
package com.example.creditzaimtest.service;

import com.example.creditzaimtest.dto.CreditDto;
import com.example.creditzaimtest.exception.CalculationException;
import com.example.creditzaimtest.mapper.Mapper;
import com.example.creditzaimtest.model.Credit;
import com.example.creditzaimtest.repository.CreditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@Slf4j
@AllArgsConstructor
public class CreditService {

    private final CreditRepository creditRepository;
    private final Mapper mapper;

    public String calculateCrossLoanPaymentString() {
        List<Credit> credits = creditRepository.findAllAndOrderByFirstPaymentDate();
        String mergePaymentStrings;
        try {
            mergePaymentStrings = mergePaymentStrings(credits);
        } catch (Exception e) {
            log.error("Error while merging payment strings", e);
            throw new CalculationException("Error while merging payment strings", e);
        }
        return mergePaymentStrings;
    }

    private String mergePaymentStrings(List<Credit> credits) {

        Map<LocalDate, Character> mergedPayments = new TreeMap<>();

        for (Credit loan : credits) {
            LocalDate startDate = loan.getFirstPaymentDate();
            String paymentString = loan.getPaymentString();
            for (int i = 0; i < paymentString.length(); i++) {
                LocalDate paymentDate = startDate.plusMonths(i);
                char paymentCode = paymentString.charAt(paymentString.length() - 1 - i);
                // Объединение платежей: выбор символа с большей просрочкой
                mergedPayments.merge(paymentDate, paymentCode, this::mergePaymentCodes);
            }
        }
        return generateCrossPaymentString(mergedPayments);
    }

    // Метод для объединения символов платежей с выбором символа с большим уровнем просрочки
    private char mergePaymentCodes(Character existingCode, char newCode) {
        Map<Character, Integer> paymentPriority = Map.of(
                'X', 0, '0', 1, '1', 2, 'A', 3, '2', 4, '3', 5
        );
        return paymentPriority.get(newCode) > paymentPriority.get(existingCode) ? newCode : existingCode;
    }

    private String generateCrossPaymentString(Map<LocalDate, Character> paymentMap) {
        StringBuilder result = new StringBuilder();

        // Итерируем по месяцам, начиная с самой ранней даты
        LocalDate firstDate = paymentMap.keySet().stream().findFirst().orElse(LocalDate.now());
        LocalDate lastDate = paymentMap.keySet().stream().reduce((first, second) -> second).orElse(LocalDate.now());

        // Проходим по всем месяцам от начала до конца, добавляя символы
        for (LocalDate date = firstDate; !date.isAfter(lastDate); date = date.plusMonths(1)) {
            result.append(paymentMap.getOrDefault(date, 'X'));
        }

        return result.reverse().toString();
    }

    public CreditDto save(CreditDto creditDto) {
        return mapper.toDataDto(creditRepository.save(mapper.toCredit(creditDto)));
    }
}
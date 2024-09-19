package com.example.creditzaimtest.mapper;

import com.example.creditzaimtest.dto.CreditDto;
import com.example.creditzaimtest.model.Credit;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Credit toCredit(CreditDto creditDto) {
        Credit credit = new Credit();
        credit.setPaymentString(creditDto.getCredit_pmt_string());
        credit.setFirstPaymentDate(creditDto.getCredit_pmt_start());
        return credit;
    }

    public CreditDto toDataDto(Credit credit) {
        CreditDto creditDto = new CreditDto();
        creditDto.setId(credit.getId());
        creditDto.setCredit_pmt_string(credit.getPaymentString());
        creditDto.setCredit_pmt_start(credit.getFirstPaymentDate());
        return creditDto;
    }

}
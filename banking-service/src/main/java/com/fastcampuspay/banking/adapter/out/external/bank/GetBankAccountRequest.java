package com.fastcampuspay.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GetBankAccountRequest {
    private String bankName;
    private String bankAccountNumber;
}

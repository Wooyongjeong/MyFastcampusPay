package com.fastcampuspay.banking.adapter.out.external.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data
public class BankAccount {
    private String bankName;
    private String bankAccountNumber;
    private boolean isValid;
}

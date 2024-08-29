package com.fastcampuspay.banking.adapter.out.external.bank;

import com.fastcampuspay.banking.adapter.out.persistence.SpringDataRegisteredBankAccountRepository;
import com.fastcampuspay.banking.application.port.out.RequestBankAccountInfoPort;
import com.fastcampuspay.banking.application.port.out.RequestExternalFirmBankingPort;
import com.fastcampuspay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BankAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmBankingPort {

    private final SpringDataRegisteredBankAccountRepository bankAccountRepository;

    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
        // 실제로 외부 은행에 http 통신을 통해 실제 은행 계좌 정보를 가져오고
        // 실제 은행 계좌 -> bankAccount

        // 우선 MVP에서는 무조건 valid한 계좌라고 가정하고 진행.
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }

    @Override
    public FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request) {
        // 실제로 외부 은행에 http 통신을 통해서
        // 펌뱅킹 요청을 하고

        // 그 결과를
        // 외부 은행의 실제 결과를 -> 패캠 페이의 FirmBankingResult 파싱
        return new FirmBankingResult(0);
    }
}

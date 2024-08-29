package com.fastcampuspay.banking.application.port.out;

import com.fastcampuspay.banking.adapter.out.external.bank.ExternalFirmBankingRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.FirmBankingResult;

public interface RequestExternalFirmBankingPort {
    FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request);
}

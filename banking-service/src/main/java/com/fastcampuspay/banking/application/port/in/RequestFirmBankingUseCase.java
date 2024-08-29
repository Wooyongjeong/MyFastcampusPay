package com.fastcampuspay.banking.application.port.in;

import com.fastcampuspay.banking.domain.FirmBankingRequest;

public interface RequestFirmBankingUseCase {
    FirmBankingRequest requestFirmBanking(FirmBankingRequestCommand command);
}

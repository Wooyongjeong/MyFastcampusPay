package com.fastcampuspay.banking.adapter.in.web;

import com.fastcampuspay.banking.application.port.in.FirmBankingRequestCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RequestFirmBankingUseCase;
import com.fastcampuspay.banking.domain.FirmBankingRequest;
import com.fastcampuspay.banking.domain.RegisteredBankAccount;
import com.fastcampuspay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RequestFirmBankingController {

    private final RequestFirmBankingUseCase requestFirmBankingUseCase;

    @PostMapping(path = "/banking/firm-banking/request")
    public RegisteredBankAccount registerMembership(@RequestBody RequestFirmBankingRequest request) {
        FirmBankingRequestCommand command = FirmBankingRequestCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();

        FirmBankingRequest firmBankingRequest = requestFirmBankingUseCase.requestFirmBanking(command);
        if (firmBankingRequest == null) {
            return null;
        }

        return null;
    }
}

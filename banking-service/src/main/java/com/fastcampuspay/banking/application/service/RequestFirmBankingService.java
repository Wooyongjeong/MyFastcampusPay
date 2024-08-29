package com.fastcampuspay.banking.application.service;

import com.fastcampuspay.banking.adapter.out.external.bank.ExternalFirmBankingRequest;
import com.fastcampuspay.banking.adapter.out.external.bank.FirmBankingResult;
import com.fastcampuspay.banking.adapter.out.persistence.FirmBankingRequestJpaEntity;
import com.fastcampuspay.banking.adapter.out.persistence.FirmBankingRequestMapper;
import com.fastcampuspay.banking.application.port.in.FirmBankingRequestCommand;
import com.fastcampuspay.banking.application.port.in.RequestFirmBankingUseCase;
import com.fastcampuspay.banking.application.port.out.RequestExternalFirmBankingPort;
import com.fastcampuspay.banking.application.port.out.RequestFirmBankingPort;
import com.fastcampuspay.banking.domain.FirmBankingRequest;
import com.fastcampuspay.common.UseCase;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmBankingService implements RequestFirmBankingUseCase {

    private final FirmBankingRequestMapper mapper;
    private final RequestFirmBankingPort requestFirmBankingPort;
    private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;

    @Override
    public FirmBankingRequest requestFirmBanking(FirmBankingRequestCommand command) {
        // Business Logic
        // a -> b 계좌

        // 1. 요청에 대한 정보를 먼저 write. "요청" 상태로
        FirmBankingRequestJpaEntity requestedEntity = requestFirmBankingPort.createFirmBankingRequest(
                new FirmBankingRequest.FromBankName(command.getFromBankName()),
                new FirmBankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(command.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmBankingRequest.FirmBankingStatus(0)
        );

        // 2. 외부 은행에 펌뱅킹 요청
        FirmBankingResult result = requestExternalFirmBankingPort.requestExternalFirmBanking(new ExternalFirmBankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName(),
                command.getToBankAccountNumber()
        ));

        // Transactional UUID
        UUID randomUUID = UUID.randomUUID();
        requestedEntity.setUuid(randomUUID);

        // 3. 결과에 따라서 1번에서 작성했던 FirmBankingRequest 정보를 Update
        if (result.getResultCode() == 0) {
            // 성공
            requestedEntity.setFirmBankingStatus(1);
        } else {
            // 실패
            requestedEntity.setFirmBankingStatus(2);
        }

        // 4. 결과를 리턴하기 전에 상태값을 기준으로 다시 save
        return mapper.mapToDomainEntity(
                requestFirmBankingPort.modifyFirmBankingRequest(requestedEntity),
                randomUUID
        );
    }
}

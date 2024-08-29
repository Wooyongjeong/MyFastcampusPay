package com.fastcampuspay.banking.adapter.out.persistence;

import com.fastcampuspay.banking.domain.FirmBankingRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FirmBankingRequestMapper {
    public FirmBankingRequest mapToDomainEntity(FirmBankingRequestJpaEntity firmBankingRequestJpaEntity, UUID uuid) {
        return FirmBankingRequest.generateFirmBankingRequest(
                new FirmBankingRequest.RequestFirmBankingId(firmBankingRequestJpaEntity.getRequestFirmBankingId()+""),
                new FirmBankingRequest.FromBankName(firmBankingRequestJpaEntity.getFromBankName()),
                new FirmBankingRequest.FromBankAccountNumber(firmBankingRequestJpaEntity.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(firmBankingRequestJpaEntity.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(firmBankingRequestJpaEntity.getToBankAccountNumber()),
                new FirmBankingRequest.MoneyAmount(firmBankingRequestJpaEntity.getMoneyAmount()),
                new FirmBankingRequest.FirmBankingStatus(firmBankingRequestJpaEntity.getFirmBankingStatus()),
                uuid
        );
    }
}

package com.fastcampuspay.banking.application.port.in;

import com.fastcampuspay.common.SelfValidating;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterBankAccountCommand extends SelfValidating<RegisterBankAccountCommand> {

    private String registeredBankAccountId;

    @NotNull
    private String membershipId;

    @NotNull
    private String bankName;

    @NotNull
    @NotBlank
    private String bankAccountNumber;

    private boolean linkedStatusIsValid;

    public RegisterBankAccountCommand(String registeredBankAccountId, String membershipId, String bankName, String bankAccountNumber, boolean linkedStatusIsValid) {
        this.registeredBankAccountId = registeredBankAccountId;
        this.membershipId = membershipId;
        this.bankName = bankName;
        this.bankAccountNumber = bankAccountNumber;
        this.linkedStatusIsValid = linkedStatusIsValid;

        this.validateSelf();
    }
}

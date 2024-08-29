package com.fastcampuspay.banking;

import com.fastcampuspay.banking.adapter.in.web.RegisterBankAccountRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest
public class RegisterBankAccountControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void testRegisterBankAccount() throws Exception {
        RegisterBankAccountRequest request = new RegisterBankAccountRequest("1", "국민은행", "123456789", true);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/banking/account/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
        )
        
        ;
    }
}

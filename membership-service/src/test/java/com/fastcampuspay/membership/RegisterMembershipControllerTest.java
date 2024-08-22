package com.fastcampuspay.membership;


import com.fastcampuspay.membership.adapter.in.web.ModifyMembershipRequest;
import com.fastcampuspay.membership.adapter.in.web.RegisterMembershipRequest;
import com.fastcampuspay.membership.domain.Membership;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterMembershipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Order(1)
    @Test
    void testRegisterMembership() throws Exception {
        RegisterMembershipRequest request = new RegisterMembershipRequest("name", "email", "address", false);

        Membership expect = Membership.generateMember(
                new Membership.MembershipId("1"),
                new Membership.MembershipName("name"),
                new Membership.MembershipEmail("email"),
                new Membership.MembershipAddress("address"),
                new Membership.MembershipIsValid(true),
                new Membership.MembershipIsCorp(false)
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/membership/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expect)));
    }

    @Order(2)
    @Test
    void testFindMembership() throws Exception {
        Membership expect = Membership.generateMember(
                new Membership.MembershipId("1"),
                new Membership.MembershipName("name"),
                new Membership.MembershipEmail("email"),
                new Membership.MembershipAddress("address"),
                new Membership.MembershipIsValid(true),
                new Membership.MembershipIsCorp(false)
        );

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/membership/1")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expect)));
    }

    @Order(3)
    @Test
    void testModifyMembership() throws Exception {
        String membershipId = "1";
        String name = "name2";
        String email = "email2";
        String address = "address2";

        ModifyMembershipRequest request = new ModifyMembershipRequest(name, email, address, true, true);

        Membership expect = Membership.generateMember(
                new Membership.MembershipId(membershipId),
                new Membership.MembershipName(name),
                new Membership.MembershipEmail(email),
                new Membership.MembershipAddress(address),
                new Membership.MembershipIsValid(true),
                new Membership.MembershipIsCorp(true)
        );


        mockMvc.perform(
                        MockMvcRequestBuilders.post("/membership/modify/" + membershipId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expect)));
    }
}

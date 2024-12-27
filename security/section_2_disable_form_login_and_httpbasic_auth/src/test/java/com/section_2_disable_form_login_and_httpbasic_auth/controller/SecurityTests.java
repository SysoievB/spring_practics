package com.section_2_disable_form_login_and_httpbasic_auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * <h6>Expected Behavior:</h6>
     *
     * When no authentication is provided, the response should be 401 Unauthorized, prompting the user to authenticate.
     * <h6>Actual Behavior:</h6>
     *
     * The response is 403 Forbidden, which indicates access is denied after authentication. This suggests Spring Security may be applying a default or anonymous authentication mechanism (e.g., AnonymousAuthenticationToken).
     * <h6>Root Cause:</h6>
     *
     * The denyAll() configuration for /notices or other global security settings could be causing Spring Security to interpret unauthenticated requests differently.*/

    @Test
    void testContactEndpointAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk());
    }

    @Test
    void testNoticesEndpointDeniedForEveryone() throws Exception {
        mockMvc.perform(get("/notices"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testPrivateEndpointsNotAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/myAccount"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/myBalance"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/myCards"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/myLoans"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("user")
    void testPrivateEndpointsAccessibleWithUserDetails() throws Exception {
        mockMvc.perform(get("/myAccount"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/myBalance"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/myCards"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/myLoans"))
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void testPrivateEndpointsNotAccessibleWithAnonymousUser() throws Exception {
        mockMvc.perform(get("/myAccount"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/myBalance"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/myCards"))
                .andExpect(status().isForbidden());

        mockMvc.perform(get("/myLoans"))
                .andExpect(status().isForbidden());
    }

    @Test
    void testFormLoginAndHttpBasicDisabled() throws Exception {
        mockMvc.perform(get("/myAccount"))
                .andExpect(status().isForbidden());
    }
}

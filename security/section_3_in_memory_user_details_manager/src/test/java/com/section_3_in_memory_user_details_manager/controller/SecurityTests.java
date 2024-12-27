package com.section_3_in_memory_user_details_manager.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPublicEndpointsAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/notices"))
                .andExpect(status().isOk());
    }

    @Test
    void testPrivateEndpointsNotAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/myAccount"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/myBalance"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/myCards"))
                .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/myLoans"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "user", authorities = {"read"})
    void testPrivateEndpointsAccessibleWithAuthentication() throws Exception {
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
    @WithMockUser(username = "admin", authorities = {"admin"})
    void testAdminAccess() throws Exception {
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
    void testInvalidUserAccess() throws Exception {
        mockMvc.perform(get("/myAccount").header("Authorization", "Basic invalid"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testFormLoginAndHttpBasicEnabled() throws Exception {
        mockMvc.perform(get("/myAccount"))
                .andExpect(status().isUnauthorized());
    }
}

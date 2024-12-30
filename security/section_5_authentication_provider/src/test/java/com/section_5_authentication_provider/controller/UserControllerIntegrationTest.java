package com.section_5_authentication_provider.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    private static final String JSON_VALID = """
            {
              "email": "verynewuser@example.com",
              "password": "secure@Password123",
              "role": "USER",
              "birthday": "2002-01-10"
            }
            """;
    private static final String JSON_NOT_VALID = """
            {
              "email": " ",
              "password": "secure@Password123",
              "role": "USER",
              "birthday": "2002-01-10"
            }
            """;
    private static final String JSON_AGE_NOT_VALID = """
            {
              "email": "verynewuser@example.com",
              "password": "secure@Password123",
              "role": "USER",
              "birthday": "2022-01-10"
            }
            """;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegisterUser_Success() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_VALID))
                .andExpect(status().isCreated())
                .andExpect(content().string("Given user details are successfully registered"));
    }

    @Test
    void testRegisterUser_UnderAge() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_AGE_NOT_VALID))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("Customer must be at least 18 years old to register"));
    }

    @Test
    void testRegisterUser_FieldNotValid() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JSON_NOT_VALID))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("User registration failed, since customer data not valid"));
    }

    @Test
    void testRegisterUser_CustomerNull() throws Exception {
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().string("User registration failed, since customer data not provided"));
    }
}

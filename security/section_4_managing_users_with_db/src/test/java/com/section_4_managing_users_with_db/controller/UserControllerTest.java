package com.section_4_managing_users_with_db.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.section_4_managing_users_with_db.config.CustomUserDetailsService;
import com.section_4_managing_users_with_db.config.SecurityConfig;
import com.section_4_managing_users_with_db.model.Customer;
import com.section_4_managing_users_with_db.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
@Import({SecurityConfig.class, CustomUserDetailsService.class})
class UserControllerTest {

    @MockitoBean
    private CustomerRepository repository;
    @MockitoBean
    private PasswordEncoder encoder;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "testuser", authorities = {"ROLE_USER"})
    void testRegisterUser() throws Exception {
        //Given
        String rawPassword = "password";
        String hashedPassword = "hashedPassword";
        Customer customer = new Customer();
        customer.setEmail("testuser@example.com");
        customer.setPassword(rawPassword);
        customer.setRole("ROLE_USER");

        given(encoder.encode(rawPassword)).willReturn(hashedPassword);
        given(repository.save(any(Customer.class))).willReturn(customer);
        given(repository.findByEmail(customer.getEmail())).willReturn(Optional.of(customer));

        String json = objectMapper.writeValueAsString(customer);

        //When & Then
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string("Given user details are successfully registered"));
    }

    @Test
    @WithAnonymousUser
    void testRegisterUserWithAnonymousUser() throws Exception {
        //Given
        String rawPassword = "password";
        String hashedPassword = "hashedPassword";
        Customer customer = new Customer();
        customer.setEmail("testuser@example.com");
        customer.setPassword(rawPassword);
        customer.setRole("ROLE_USER");

        given(encoder.encode(rawPassword)).willReturn(hashedPassword);
        given(repository.save(any(Customer.class))).willReturn(customer);
        given(repository.findByEmail(customer.getEmail())).willReturn(Optional.of(customer));

        String json = objectMapper.writeValueAsString(customer);

        //When & Then
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string("Given user details are successfully registered"));
    }

    @Test
    @WithMockUser(username = "testuser", authorities = {"ROLE_USER"})
    void testRegisterUserWhenBadRequest() throws Exception {
        //Given
        String rawPassword = "password";
        Customer customer = new Customer();
        customer.setEmail("testuser@example.com");
        customer.setPassword(rawPassword);
        customer.setRole("ROLE_USER");

        String json = objectMapper.writeValueAsString(customer);

        //When & Then
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User registration failed"));
    }
}
package com.section_1_static_credentials;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {
    private static final String URI = "/hello";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenUnauthenticated_thenAccessDenied() throws Exception {
        mockMvc.perform(get(URI))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "root")
    public void whenUserAuthenticated_thenAccessGranted() throws Exception {
        mockMvc.perform(get(URI))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from rest controller"));
    }
}

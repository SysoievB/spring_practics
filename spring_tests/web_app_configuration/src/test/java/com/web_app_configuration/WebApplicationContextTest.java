package com.web_app_configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@WebAppConfiguration
public class WebApplicationContextTest {
    private static final String TEST_CONFIG_BEAN = "Test Config Bean";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void contextLoads() {
        assertThat(webApplicationContext).isNotNull();
    }

    @Test
    public void verifyBeanPresence() {
        // Check if a specific bean is present in the WebApplicationContext
        boolean beanExists = webApplicationContext.containsBean("testConfig");
        assertThat(beanExists).isTrue();

        String str = (String) webApplicationContext.getBean("testConfig");
        assertThat(str).isEqualTo(TEST_CONFIG_BEAN);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        public String testConfig() {
            return TEST_CONFIG_BEAN;
        }
    }
}

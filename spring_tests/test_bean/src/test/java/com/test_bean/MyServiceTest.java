package com.test_bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.bean.override.convention.TestBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(MyConfig.class)
class MyServiceTest {

    @TestBean
    MyService myTestService;

    @Autowired
    ApplicationContext context;

    // Define the test bean instance
    static MyService myTestService() {
        return new MyService() {
            @Override
            public String greeting(String name) {
                return "Test Hello, " + name;
            }
        };
    }

    @Test
    void test() {
        // Ensure the bean was overridden correctly
        assertThat(context.getBean("myService"))
                .isSameAs(this.myTestService)
                .isInstanceOf(MyService.class);

        // Verify overridden behavior
        MyService service = (MyService) context.getBean("myService");
        assertThat(service.greeting("World")).isEqualTo("Test Hello, World");
    }
}
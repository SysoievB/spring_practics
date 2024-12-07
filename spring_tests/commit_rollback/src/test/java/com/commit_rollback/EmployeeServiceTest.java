package com.commit_rollback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class EmployeeServiceTest {

    @MockitoBean
    EmployeeRepo repo;

    @Autowired
    EmployeeService service;

    @BeforeTransaction
    void setUp() {
        repo.deleteAll();
    }

    @AfterTransaction
    void tearDown() {
        repo.deleteAll();
    }

    @Transactional
    @Commit // the same as @Rollback(false)
    @Test
    void test() {

    }

    @Transactional
    @Rollback
    @Test
    void test1() {

    }
}
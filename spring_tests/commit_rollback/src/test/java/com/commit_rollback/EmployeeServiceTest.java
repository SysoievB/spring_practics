package com.commit_rollback;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    EmployeeRepo repository;

    @Autowired
    EmployeeService service;

    @BeforeTransaction
    void setUp() {
        repository.deleteAll();
    }

    @AfterTransaction
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    @Transactional
    @Commit// Persist changes to the DB after the test
    void shouldCommitWhenEmployeeIsCreated() {
        // Given
        String employeeName = "Jane Doe";
        assertEquals(0, repository.count());

        // When
        Employee result = service.getEmployeeByNameOrCreateIfNotExists(employeeName);

        // Then
        assertNotNull(result);
        assertEquals(employeeName, result.getName());
        assertEquals(1, repository.count());
    }

    @Test
    @Transactional
    @Rollback// Roll back changes after the test
    void shouldRollbackWhenEmployeeIsCreated() {
        // Given
        String employeeName = "John Doe";
        assertEquals(0, repository.count());

        // When
        Employee result = service.getEmployeeByNameOrCreateIfNotExists(employeeName);

        // Then
        assertNotNull(result);
        assertEquals(employeeName, result.getName());
        assertEquals(1, repository.count());
    }
}
package com.sql_annotations;

import org.hibernate.Session;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:test_application.properties")
@SqlGroup({
        @Sql(scripts = {"classpath:schema.sql"},
                executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                config = @SqlConfig(encoding = "utf-8")),
        @Sql(statements = "DROP TABLE employee",
                executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
})
class EmployeeRepoH2Test {
    private static final String DIALECT = "org.hibernate.dialect.H2Dialect";
    @Autowired
    private EmployeeRepo repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testDatabaseConnection() {
        String url = entityManager.getEntityManager().unwrap(Session.class)
                .getSessionFactory()
                .getProperties()
                .get("hibernate.dialect")
                .toString();

        assertThat(url).isEqualTo(DIALECT);
    }

    @Test
    void testFindEmployeeById() {
        //Given
        Employee employee = repo.save(new Employee("Vasia", "vasia@mail.com"));
        entityManager.flush();
        entityManager.clear();
        Long id = employee.getId();

        //When
        Employee result = repo.findByNameIsContainingIgnoreCase("as").get();

        //Then
        assertThat(result)
                .isNotNull()
                .returns(id, Employee::getId)
                .returns("Vasia", Employee::getName)
                .returns("vasia@mail.com", Employee::getEmail);
    }
}

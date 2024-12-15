package com.sql_annotations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.hibernate.Session;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <h3>@DataJpaTest</h3> has an annotation @AutoConfigureTestDatabase, which by default, sets up an
 * H2 in-memory database for the tests, and configures DataSource to use it.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//uses MySQL
class EmployeeRepoTest {
    private static final String DIALECT = "org.hibernate.dialect.MySQLDialect";
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
        Employee result = repo.findById(id).get();

        //Then
        assertThat(result)
                .isNotNull()
                .returns(id, Employee::getId)
                .returns("Vasia", Employee::getName)
                .returns("vasia@mail.com", Employee::getEmail);
    }
}
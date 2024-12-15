package com.sql_annotations;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = {"classpath:schema.sql"})
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)//can be set here or on test method
class EmployeeRepoSQLMergeModeTest {
    @Autowired
    private EmployeeRepo repo;


    @Sql(scripts = {"classpath:populate.sql"})
    @Test
    //@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
    void testCountEmployees() {
        //When
        long result = repo.countAllByName("Valera");

        //Then
        assertThat(result).isEqualTo(3L);
    }
}

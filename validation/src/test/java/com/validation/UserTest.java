package com.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class UserTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void user_fields_assertions() {
        //given
        val user = new User();
        user.setName(null);
        user.setSurname("");
        user.setFullname("           ");
        user.setAge(17);
        user.setWorking(false);
        user.setSalary(-100);
        user.setDebt(1000);
        user.setNextSalaryDate(LocalDate.now().minusMonths(1));
        user.setLastSalaryDate(LocalDate.now().plusMonths(1));
        user.setPreferences(List.of("   "));
        user.setEmail("vasia123");
        user.setDateOfBirth(LocalDate.now().minusYears(20));

        //when
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        //then
        assertThat(violations)
                .extracting(ConstraintViolation::getMessage)
                .containsExactlyInAnyOrder(
                        "Name cannot be null",
                        "Surname cannot be empty",
                        "Fullname cannot be blank",
                        "Age should not be less than 18",
                        "Working must be true",
                        "Salary cannot be less than zero",
                        "Debt cannot be greater than zero",
                        "Salary date cannot be in the past",
                        "Last salary date cannot be in future",
                        "Email should be valid",
                        "Cannot be blank"
                );
    }
}

package com.validation;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NegativeOrZero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotEmpty(message = "Surname cannot be empty")
    private String surname;

    @NotBlank(message = "Fullname cannot be blank")
    private String fullname;

    @AssertTrue(message = "Working must be true")
    private boolean working;

    @Size(min = 10, max = 200, message
            = "About Me must be between 10 and 200 characters")
    private String aboutMe;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be greater than 150")
    private int age;

    @PositiveOrZero(message = "Salary cannot be less than zero")
    private int salary;

    @NegativeOrZero(message = "Debt cannot be greater than zero")
    private int debt;

    @FutureOrPresent(message = "Salary date cannot be in the past")
    private LocalDate nextSalaryDate;

    @PastOrPresent(message = "Last salary date cannot be in future")
    private LocalDate lastSalaryDate;

    @Email(regexp = "[abc]",
            message = "Email should be valid")
    private String email;

    List<@NotBlank(message = "Cannot be blank") String> preferences;

    private LocalDate dateOfBirth;

    public Optional<@Past LocalDate> getDateOfBirth() {
        return Optional.of(dateOfBirth);
    }
}

package com.stored_procedures.named_procedure;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@NamedStoredProcedureQuery(
        name = "User.GET_USERS_BY_AGE",
        procedureName = "GET_USERS_BY_AGE",
        resultClasses = User.class,
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "ageParam", type = Integer.class)
        }
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

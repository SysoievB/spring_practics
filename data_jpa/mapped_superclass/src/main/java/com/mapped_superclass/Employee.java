package com.mapped_superclass;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "employee_name")),
        @AttributeOverride(name = "surname", column = @Column(name = "employee_surname"))
})
public class Employee extends Person {
    private String company;
}

package com.formula_annotation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Formula;

import java.io.Serializable;

@Entity
@Table
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long grossIncome;

    private int taxInPercents;

    @Formula("gross_income * tax_in_percents / 100")
    private long tax;

    public Employee(long grossIncome, int taxInPercents) {
        this.grossIncome = grossIncome;
        this.taxInPercents = taxInPercents;
    }
}

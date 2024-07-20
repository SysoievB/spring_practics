package com.filter_annotation;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
import org.hibernate.annotations.Where;

@Entity
@Table
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Where(clause = "status = 'EVEN'")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    Status status;

    public Employee(Long id, String name) {
        this.id = id;
        this.name = name;
        this.status = id % 2 == 0 ? Status.EVEN : Status.ODD;
    }

    enum Status {
        ODD, EVEN
    }
}


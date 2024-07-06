package com.generationtype_table;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "id_generator")
    @TableGenerator(
            name = "id_generator", // The name used to refer to this generator
            table = "user_ids",    // The table that stores the generated values
            pkColumnName = "gen_name",  // The column name for the generator names
            valueColumnName = "gen_value", // The column name for the generated values
            allocationSize = 1 // Optional: the number by which the sequence will be incremented
    )
    private Long id;

    private String name;

    public User(String name) {
        this.name = name;
    }
}

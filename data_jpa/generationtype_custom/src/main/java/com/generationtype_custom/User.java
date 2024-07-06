package com.generationtype_custom;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

@Entity
@Table
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "string_prefixed_generator")
    @GenericGenerator(
            name = "string_prefixed_generator",
            type = SequenceStyleGenerator.class,
            strategy = "com.generationtype_custom.MyGenerator",
            parameters = {
                    @Parameter(name = MyGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = MyGenerator.VALUE_PREFIX_PARAMETER, value = "B_"),
                    @Parameter(name = MyGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")}
    )
    private String id;

    private String name;

    public User(String name) {
        this.name = name;
    }
}

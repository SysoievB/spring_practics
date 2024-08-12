package com.attribute_converter;

import jakarta.persistence.Convert;
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

@Entity
@Table(name = "email")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Convert(converter = StringToByteArrayAttributeConverter.class)
    String massage;

    String recipient;//varbinary(255) because of autoApply = true

    public Email(String massage, String recipient) {
        this.massage = massage;
        this.recipient = recipient;
    }
}

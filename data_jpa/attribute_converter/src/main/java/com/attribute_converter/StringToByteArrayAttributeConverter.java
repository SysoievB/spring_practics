package com.attribute_converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Optional;

/**
 * autoApply = true - tells the JPA provider that this converter should be automatically
 * applied to all entity attributes of the specified target type across the entire persistence unit
 * */
@Converter(autoApply = true)
public class StringToByteArrayAttributeConverter implements AttributeConverter<String, byte[]> {
    @Override
    public byte[] convertToDatabaseColumn(String attribute) {
        return Optional.of(attribute).orElse("").getBytes();
    }

    @Override
    public String convertToEntityAttribute(byte[] dbData) {
        return new String(dbData);
    }
}

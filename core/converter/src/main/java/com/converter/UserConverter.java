package com.converter;

import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserConverter implements Converter<User, UserDTO> {
    private final PasswordEncoderConverter passwordEncoderConverter;
    private final UserToDTOConverter userToDTOConverter;

    @Override
    public UserDTO convert(User source) {
        return passwordEncoderConverter
                .andThen(userToDTOConverter)
                .convert(source);
    }
}

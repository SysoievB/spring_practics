package com.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToDTOConverter implements Converter<User, UserDTO> {
    @Override
    public UserDTO convert(User source) {
        return new UserDTO(source.getUsername(), source.getPassword());
    }
}

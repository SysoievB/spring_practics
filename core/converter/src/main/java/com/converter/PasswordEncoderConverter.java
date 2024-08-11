package com.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static java.util.Base64.getEncoder;

@Component
public class PasswordEncoderConverter implements Converter<User, User> {
    @Override
    public User convert(User source) {
        return Optional.of(source)
                .map(user -> user.setPassword(
                        getEncoder().encodeToString(source.getPassword().getBytes(StandardCharsets.UTF_8)))
                )
                .orElse(source);
    }
}

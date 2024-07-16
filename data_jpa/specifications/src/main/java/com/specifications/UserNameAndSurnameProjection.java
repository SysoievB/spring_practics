package com.specifications;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "userNameAndSurnameProjection", types = {User.class})
public interface UserNameAndSurnameProjection {
    String getName();

    String getSurname();
}

package com.webflux.simple_web;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface UserRepo extends R2dbcRepository<User, Long> {
}

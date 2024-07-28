package com.webflux;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepo repo;

    Mono<List<User>> getAllUsers() {
        return repo.findAll().collectList();
    }

    Mono<User> findUserById(Long id) {
        return repo.findById(id);
    }

    @Transactional
    Mono<User> updateUser(UserDto dto, Long id) {
      return findUserById(id)
              .map(user -> {
                  user.setName(dto.name());
                  user.setAge(dto.age());
                  return user;
              })
              .defaultIfEmpty(new User(id, dto.name(), dto.age()));
    }

    @Transactional
    Mono<User> createUser(UserDto dto) {
        return repo.save(new User(dto.name(), dto.age()));
    }

    @Transactional
    Mono<Void> deleteUser(Long id) {
        return findUserById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User not found with id: " + id)))
                .flatMap(repo::delete);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @NoArgsConstructor
    static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String message) {
            super(message);
        }
    }
}

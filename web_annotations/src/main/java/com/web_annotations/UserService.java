package com.web_annotations;

import lombok.val;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.isNull;

@Component
public class UserService {
    private List<User> users = new ArrayList<>();

    {
        users.add(new User("John", "Doe", 2000, LocalDate.of(1999, 3, 15)));
        users.add(new User("Jane", "Smith", 3000, LocalDate.of(1994, 7, 20)));
        users.add(new User("Michael", "Johnson", 3500, LocalDate.of(1988, 2, 10)));
        users.add(new User("Emily", "Davis", 2200, LocalDate.of(2002, 1, 5)));
        users.add(new User("Chris", "Brown", 2800, LocalDate.of(1996, 8, 25)));
        users.add(new User("Sarah", "Wilson", 3100, LocalDate.of(1993, 11, 30)));
        users.add(new User("David", "Martinez", 2700, LocalDate.of(1997, 5, 17)));
        users.add(new User("Laura", "Garcia", 2400, LocalDate.of(2000, 6, 10)));
        users.add(new User("Daniel", "Anderson", 2900, LocalDate.of(1995, 12, 23)));
        users.add(new User("Emma", "Taylor", 2600, LocalDate.of(1998, 4, 1)));
    }

    List<User> getAllUsers() {
        return users;
    }

    Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> Objects.equals(user.getId(), id))
                .findFirst();
    }

    User createNewUser(UserDto dto) {
        val user = new User(dto.name(), dto.surname(), dto.salary(), dto.birthDate());
        users.add(user);
        System.out.println(user);
        return user;
    }

    User updateUser(UserDto dto, Long id) {
        val updatedUser = findById(id)
                .map(user -> user.update(
                                validate(dto.name()),
                                validate(dto.surname()),
                                dto.salary(),
                                isNull(dto.birthDate()) ? user.getBirthDate() : dto.birthDate())
                )
                .orElse(new User(dto.name(), dto.surname(), dto.salary(), dto.birthDate()));
        users.add(updatedUser);
        System.out.println(updatedUser);
        return updatedUser;
    }

    int deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id)) ? 1 : -1;
    }

    private String validate(String s) {
        return s.strip();
    }
}

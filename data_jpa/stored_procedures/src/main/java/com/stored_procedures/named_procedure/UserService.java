package com.stored_procedures.named_procedure;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public List<User> findUsersByAge(Integer age) {
        return userRepository.getUsersByAge(age);
    }
}


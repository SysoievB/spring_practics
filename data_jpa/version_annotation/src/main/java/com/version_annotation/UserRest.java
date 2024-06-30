package com.version_annotation;

import com.version_annotation.entity.User;
import com.version_annotation.entity.UserRepo;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Validated
public class UserRest {
    private final UserRepo repo;

    @PostMapping
    public User create() {
        return repo.save(new User("Vasia", "Pupkin", 33));
    }

    @PutMapping
    public User update() {
        val user = repo.findById(1L).get();
        user.setName("New user");
        user.setSurname("New sur");
        return repo.save(user);
    }
}

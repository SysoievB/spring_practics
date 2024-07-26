package com.web_annotations;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService service;
    private final Translator translator;

    @GetMapping
    ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getById(@PathVariable Long id) {
        val user = service.findById(id)
                .orElseThrow(RestResponseEntityExceptionHandler.UserNotFoundException::new);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDto dto) {
        return ResponseEntity.ok(service.updateUser(dto, id));
    }

    @PatchMapping("/{id}")
    ResponseEntity<User> patchByName(@PathVariable Long id, @RequestParam String name) {
        return ResponseEntity.ok(service.patchName(name, id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    ResponseEntity<User> create(@RequestBody UserDto request) {
        return ResponseEntity.ok(service.createNewUser(request));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        service.deleteUser(id);
    }

    @GetMapping("/lang")
    ResponseEntity<String> getTranslate(
            @RequestHeader(name = HttpHeaders.ACCEPT_LANGUAGE, required = false) String lang,
            @RequestParam String forTranslation) {
        return ResponseEntity.ok(translator.translate(lang, forTranslation));
    }
}

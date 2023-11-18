package com.horacioing.ivstore.infrastructure.controllers.user;

import com.horacioing.ivstore.application.services.UserService;
import com.horacioing.ivstore.domain.models.user.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
@Tag(name = "User", description = "User API")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody
            DataUserRegister dataRegister,
            UriComponentsBuilder uriBuilder
    ) {
        User userSaved = this.userService.createUser(
                User.builder()
                        .email(dataRegister.email())
                        .password(dataRegister.password())
                        .employee(dataRegister.employee())
                        .build()
        );

        return ResponseEntity.created(
                uriBuilder.path("api/v1/users/{userId}")
                        .buildAndExpand(userSaved.getId())
                        .toUri()
        ).body(userSaved);
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(Pageable pagination) {
        return ResponseEntity.ok(this.userService.getAllUsers(pagination));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(
            @PathVariable(name = "userId")
            Long id
    ) {
        Optional<User> userFound = this.userService.getUser(id);
        return userFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

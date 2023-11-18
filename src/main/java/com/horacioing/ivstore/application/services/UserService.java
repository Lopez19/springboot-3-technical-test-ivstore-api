package com.horacioing.ivstore.application.services;

import com.horacioing.ivstore.domain.models.user.User;
import com.horacioing.ivstore.domain.ports.in.user.CreateUserUseCase;
import com.horacioing.ivstore.domain.ports.in.user.RetrieveUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class UserService implements CreateUserUseCase, RetrieveUserUseCase {

    private final CreateUserUseCase createUserUseCase;
    private final RetrieveUserUseCase retrieveUserUseCase;

    @Override
    public User createUser(User user) {
        return this.createUserUseCase.createUser(user);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return this.retrieveUserUseCase.getUser(id);
    }

    @Override
    public Page<User> getAllUsers(Pageable pagination) {
        return this.retrieveUserUseCase.getAllUsers(pagination);
    }
}

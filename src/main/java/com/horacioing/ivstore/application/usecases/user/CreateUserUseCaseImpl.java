package com.horacioing.ivstore.application.usecases.user;

import com.horacioing.ivstore.domain.models.user.User;
import com.horacioing.ivstore.domain.ports.in.user.CreateUserUseCase;
import com.horacioing.ivstore.domain.ports.out.UserRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public User createUser(User user) {
        return this.userRepositoryPort.save(user);
    }
}

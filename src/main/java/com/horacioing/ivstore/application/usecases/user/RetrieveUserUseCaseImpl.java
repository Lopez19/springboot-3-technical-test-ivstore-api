package com.horacioing.ivstore.application.usecases.user;

import com.horacioing.ivstore.domain.models.user.User;
import com.horacioing.ivstore.domain.ports.in.user.RetrieveUserUseCase;
import com.horacioing.ivstore.domain.ports.out.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class RetrieveUserUseCaseImpl implements RetrieveUserUseCase {

    private final UserRepositoryPort userRepositoryPort;

    @Override
    public Optional<User> getUser(Long id) {
        return this.userRepositoryPort.findById(id);
    }

    @Override
    public Page<User> getAllUsers(Pageable pagination) {
        return this.userRepositoryPort.findAll(pagination);
    }
}

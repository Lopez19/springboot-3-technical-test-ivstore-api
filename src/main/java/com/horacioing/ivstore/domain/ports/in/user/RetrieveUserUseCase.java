package com.horacioing.ivstore.domain.ports.in.user;

import com.horacioing.ivstore.domain.models.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RetrieveUserUseCase {
    Optional<User> getUser(Long id);
    Page<User> getAllUsers(Pageable pagination);
}

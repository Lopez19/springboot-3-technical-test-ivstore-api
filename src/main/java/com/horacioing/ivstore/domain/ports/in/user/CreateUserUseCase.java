package com.horacioing.ivstore.domain.ports.in.user;

import com.horacioing.ivstore.domain.models.user.User;

public interface CreateUserUseCase {
    User createUser(User user);
}

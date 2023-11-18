package com.horacioing.ivstore.infrastructure.config;

import com.horacioing.ivstore.application.services.UserService;
import com.horacioing.ivstore.application.usecases.user.CreateUserUseCaseImpl;
import com.horacioing.ivstore.application.usecases.user.RetrieveUserUseCaseImpl;
import com.horacioing.ivstore.domain.ports.out.UserRepositoryPort;
import com.horacioing.ivstore.infrastructure.adapters.mysql.UserRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeansConfig {
    @Bean
    public UserService userService(UserRepositoryPort userRepositoryPort){
        return new UserService(
                new CreateUserUseCaseImpl(userRepositoryPort),
                new RetrieveUserUseCaseImpl(userRepositoryPort)
        );
    }

    @Bean
    public UserRepositoryPort userRepositoryPort(UserRepositoryAdapter userRepositoryAdapter){
        return userRepositoryAdapter;
    }
}

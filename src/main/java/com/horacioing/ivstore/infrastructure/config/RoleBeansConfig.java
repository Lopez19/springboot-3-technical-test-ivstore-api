package com.horacioing.ivstore.infrastructure.config;

import com.horacioing.ivstore.application.services.RoleService;
import com.horacioing.ivstore.application.usecases.role.CreateRoleUseCaseImpl;
import com.horacioing.ivstore.application.usecases.role.RetrieveRoleUseCaseImpl;
import com.horacioing.ivstore.domain.ports.out.RoleRepositoryPort;
import com.horacioing.ivstore.infrastructure.adapters.mysql.RoleRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleBeansConfig {
    @Bean
    public RoleService roleService(RoleRepositoryPort roleRepositoryPort){
        return new RoleService(
                new CreateRoleUseCaseImpl(roleRepositoryPort),
                new RetrieveRoleUseCaseImpl(roleRepositoryPort)
        );
    }

    @Bean
    public RoleRepositoryPort roleRepositoryPort(RoleRepositoryAdapter roleRepositoryAdapter){
        return roleRepositoryAdapter;
    }
}

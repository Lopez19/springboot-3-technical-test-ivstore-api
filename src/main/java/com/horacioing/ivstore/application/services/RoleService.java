package com.horacioing.ivstore.application.services;

import com.horacioing.ivstore.domain.models.role.Role;
import com.horacioing.ivstore.domain.ports.in.role.CreateRoleUseCase;
import com.horacioing.ivstore.domain.ports.in.role.RetrieveRoleUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class RoleService implements CreateRoleUseCase, RetrieveRoleUseCase {

    private final CreateRoleUseCase createRoleUseCase;
    private final RetrieveRoleUseCase retrieveRoleUseCase;

    @Override
    public Role createRole(Role role) {
        return this.createRoleUseCase.createRole(role);
    }

    @Override
    public Optional<Role> getRole(Long id) {
        return this.retrieveRoleUseCase.getRole(id);
    }

    @Override
    public Page<Role> getAllRoles(Pageable pagination) {
        return this.retrieveRoleUseCase.getAllRoles(pagination);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return this.retrieveRoleUseCase.getRoleByName(name);
    }
}

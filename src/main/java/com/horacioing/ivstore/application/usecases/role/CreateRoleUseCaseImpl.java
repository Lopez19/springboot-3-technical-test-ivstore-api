package com.horacioing.ivstore.application.usecases.role;

import com.horacioing.ivstore.domain.models.role.Role;
import com.horacioing.ivstore.domain.ports.in.role.CreateRoleUseCase;
import com.horacioing.ivstore.domain.ports.out.RoleRepositoryPort;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class CreateRoleUseCaseImpl implements CreateRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public Role createRole(Role role) {
        Optional<Role> roleFound = this.roleRepositoryPort.findByName(role.getName());
        return roleFound.orElseGet(() -> this.roleRepositoryPort.save(role));
    }
}

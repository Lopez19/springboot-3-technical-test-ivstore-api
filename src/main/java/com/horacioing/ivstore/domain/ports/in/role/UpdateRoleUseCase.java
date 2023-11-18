package com.horacioing.ivstore.domain.ports.in.role;

import com.horacioing.ivstore.domain.models.role.Role;

import java.util.Optional;

public interface UpdateRoleUseCase {
    Optional<Role> updateRole(Long id, Role role);
}

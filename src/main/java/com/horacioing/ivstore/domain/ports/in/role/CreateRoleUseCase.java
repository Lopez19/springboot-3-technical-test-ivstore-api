package com.horacioing.ivstore.domain.ports.in.role;

import com.horacioing.ivstore.domain.models.role.Role;

public interface CreateRoleUseCase {
    Role createRole(Role role);
}

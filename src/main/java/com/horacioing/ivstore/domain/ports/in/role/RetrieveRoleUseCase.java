package com.horacioing.ivstore.domain.ports.in.role;

import com.horacioing.ivstore.domain.models.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface RetrieveRoleUseCase {
    Optional<Role> getRole(Long id);
    Page<Role> getAllRoles(Pageable pagination);
    Optional<Role> getRoleByName(String name);
}

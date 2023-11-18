package com.horacioing.ivstore.application.usecases.role;

import com.horacioing.ivstore.domain.models.role.Role;
import com.horacioing.ivstore.domain.ports.in.role.RetrieveRoleUseCase;
import com.horacioing.ivstore.domain.ports.out.RoleRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

@AllArgsConstructor
public class RetrieveRoleUseCaseImpl implements RetrieveRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public Optional<Role> getRole(Long id) {
        return this.roleRepositoryPort.findById(id);
    }

    @Override
    public Page<Role> getAllRoles(Pageable pagination) {
        return this.roleRepositoryPort.findAll(pagination);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return this.roleRepositoryPort.findByName(name);
    }
}

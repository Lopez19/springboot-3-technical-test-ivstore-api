package com.horacioing.ivstore.infrastructure.adapters.mysql;

import com.horacioing.ivstore.domain.models.role.Role;
import com.horacioing.ivstore.domain.ports.out.RoleRepositoryPort;
import com.horacioing.ivstore.infrastructure.persistence.entities.RoleEntity;
import com.horacioing.ivstore.infrastructure.persistence.mappers.RoleMapper;
import com.horacioing.ivstore.infrastructure.persistence.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {

    private final RoleRepository repository;
    private final RoleMapper roleMapper;

    @Override
    public Role save(Role role) {
        RoleEntity entity = this.repository.save(
                RoleEntity.builder()
                        .name(role.getName().replace(" ", "_").toUpperCase())
                        .build()
        );
        return this.roleMapper.toDomain(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!this.repository.existsById(id)){
            return Boolean.FALSE;
        }
        this.repository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Optional<Role> findById(Long id) {
        return this.repository.findById(id).map(this.roleMapper::toDomain);
    }

    @Override
    public Page<Role> findAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(this.roleMapper::toDomain);
    }

    @Override
    public Optional<Role> update(Long id, Role role) {
        return Optional.empty();
    }

    @Override
    public Optional<Role> findByName(String name) {
        return this.repository.findByName(
                name.replace(" ", "_").toUpperCase()
        ).map(this.roleMapper::toDomain);
    }
}

package com.horacioing.ivstore.infrastructure.adapters.mysql;

import com.horacioing.ivstore.domain.models.user.User;
import com.horacioing.ivstore.domain.ports.out.UserRepositoryPort;
import com.horacioing.ivstore.infrastructure.persistence.entities.UserEntity;
import com.horacioing.ivstore.infrastructure.persistence.mappers.UserMapper;
import com.horacioing.ivstore.infrastructure.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository repository;
    private final UserMapper userMapper;

    @Override
    public User save(User user) {
        UserEntity entity = this.repository.save(this.userMapper.toEntity(user));
        return this.userMapper.toDomain(entity);
    }

    @Override
    public boolean deleteById(Long id) {
        if (!this.repository.existsById(id)) {
            return Boolean.FALSE;
        }
        this.repository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.repository.findById(id).map(this.userMapper::toDomain);
    }

    @Override
    public Page<User> findAll(Pageable pagination) {
        return this.repository.findAll(pagination).map(this.userMapper::toDomain);
    }

    @Override
    public Optional<User> update(Long id, User user) {
        return Optional.empty();
    }
}

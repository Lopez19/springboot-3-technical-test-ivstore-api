package com.horacioing.ivstore.infrastructure.persistence.repositories;

import com.horacioing.ivstore.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}

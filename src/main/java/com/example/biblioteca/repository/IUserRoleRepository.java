package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRoleRepository extends JpaRepository<UserRole,Long> {
    Optional<UserRole> findByType (String type);
}

package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
        Role findRoleByType (String tipo);
}

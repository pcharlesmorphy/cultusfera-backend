package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Musician;
import com.example.biblioteca.model.entity.Role;
import com.example.biblioteca.model.entity.User;
import com.example.biblioteca.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByUsername (String username);

    Optional<User> findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(String name, String surnames);
    List<User> findUsersByRole (UserRole role);

}

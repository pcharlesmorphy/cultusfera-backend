package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Role;
import com.example.biblioteca.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> findAll ();
    User save(User user);
    Boolean delete(Long id);
    User update (User user);
    Optional<User> findById (Long id);
    Optional<User> findUserByUsername (String username);
    List<User> findClientUsers ();

    Optional<User> findByName (String name, String surnames);

}

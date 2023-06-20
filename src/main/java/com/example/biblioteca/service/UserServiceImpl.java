package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Musician;
import com.example.biblioteca.model.entity.Role;
import com.example.biblioteca.model.entity.User;
import com.example.biblioteca.model.entity.UserRole;
import com.example.biblioteca.repository.IUserRepository;
import com.example.biblioteca.repository.IUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    IUserRepository userRepo;

    @Autowired
    IUserRoleRepository userRoleRepo;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findClientUsers (){
        Optional<UserRole> role = findByType("User");
        return userRepo.findUsersByRole(role.get());
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepo.save(user);
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        if (findById(id).isEmpty()) return false;
        userRepo.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public User update(User user) {
        return userRepo.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(userRepo.findById(id)).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(userRepo.findUserByUsername(username)).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByName(String name, String surnames) {
        return Optional.ofNullable(userRepo.findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(name,surnames).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserRole> findByType(String type){
        return userRoleRepo.findByType(type);
    }

}

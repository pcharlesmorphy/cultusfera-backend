package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Penalty;
import com.example.biblioteca.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPenaltyRepository extends JpaRepository<Penalty,Long> {
    List<Penalty> findAllByUser(User user);
}

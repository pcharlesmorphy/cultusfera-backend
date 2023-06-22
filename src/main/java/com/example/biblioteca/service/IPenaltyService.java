package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Penalty;
import com.example.biblioteca.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface IPenaltyService {
    List<Penalty> findAllPenalties ();
    //List<Penalty> findByName(String name, String surnames);
    Penalty save(Penalty Penalty);
    //Boolean delete(Long id);
    //Optional<Penalty> update (Penalty Penalty);
    List<Penalty> findByUser (User user);
}

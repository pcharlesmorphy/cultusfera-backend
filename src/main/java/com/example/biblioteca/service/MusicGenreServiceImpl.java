package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.MusicGenre;
import com.example.biblioteca.repository.IMusicGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MusicGenreServiceImpl implements IMusicGenreService {

    @Autowired
    private IMusicGenreRepository generoMusicalRepo;

    @Override
    @Transactional(readOnly = true)
    public List<MusicGenre> findAll (){

        return generoMusicalRepo.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MusicGenre> findById (Long id){
        return generoMusicalRepo.findById(id);
    }
}

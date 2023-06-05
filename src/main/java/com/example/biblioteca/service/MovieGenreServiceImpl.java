package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.MovieGenre;
import com.example.biblioteca.repository.IMovieGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MovieGenreServiceImpl implements IMovieGenreService {
    @Autowired
    private IMovieGenreRepository generoPeliculaRepo;

    @Override
    @Transactional(readOnly = true)
    public List<MovieGenre> findAll (){

        return generoPeliculaRepo.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MovieGenre> findById (Long id){
        return generoPeliculaRepo.findById(id);
    }
}

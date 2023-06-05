package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.LiteraryGenre;
import com.example.biblioteca.repository.ILiteraryGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LiteraryGenreServiceImpl implements ILiteraryGenreService {

    @Autowired
    private ILiteraryGenreRepository generoLiterarioRepo;

    @Override
    @Transactional(readOnly = true)
    public List<LiteraryGenre> findAll (){
        return generoLiterarioRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LiteraryGenre> findById (Long id){
        return Optional.ofNullable(generoLiterarioRepo.findById(id).orElse(null));
    }
}

package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Director;
import com.example.biblioteca.model.entity.Movie;
import com.example.biblioteca.repository.IDirectorRepository;
import com.example.biblioteca.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DirectorServiceImpl implements IDirectorService{
    @Autowired
    private IDirectorRepository directorRepo;

    @Autowired
    private IMovieService movieService;

    @Override
    @Transactional(readOnly = true)
    public List<Director> findAll() {
        return directorRepo.findAll();
    }

    @Override
    public Optional<Director> save(Director director) {
        if (!checkDuplicatedDirector(director)){
            return Optional.of(directorRepo.save(director));
        }
        return Optional.empty();
    }


    @Override
    public Optional<Director> update(Director director) {
        if (!checkDuplicatedDirector(director)){
            return Optional.of(directorRepo.save(director));
        }
        return Optional.empty();
    }

    private Boolean checkDuplicatedDirector (Director director){
        List<Director> directors = directorRepo.findAllByNameEqualsIgnoreCaseAndSurnamesEqualsIgnoreCase(director.getName(), director.getSurnames());
        if (directors.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Director> director = findById(id);
        if (director.isEmpty()) return false;
        //Si el director tiene alguna película en la base de datos no se puede eliminar

        List<Movie> directorMovies = movieService.findAllByDirector(director.get());
        if (!directorMovies.isEmpty()) return false;

        directorRepo.deleteById(id);
        return true;
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<Director> findById(Long id) {
        return Optional.ofNullable(directorRepo.findById(id)).orElse(null);
    }


}

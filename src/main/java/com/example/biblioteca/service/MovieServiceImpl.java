package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.repository.IActorRepository;
import com.example.biblioteca.repository.IDirectorRepository;
import com.example.biblioteca.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    IMovieRepository movieRepo;

    @Autowired
    IDirectorRepository directorRepo;

    @Autowired
    IActorRepository actorRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieRepo.findAll();
    }

    @Override
    @Transactional
    public Optional<Movie> save(Movie movie) {
        if (!checkDuplicatedMovies(movie)){
            movie.setRating(0.0);
            movie.setTotalReviews(0);
            return Optional.of(movieRepo.save(movie));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Movie> update(Movie movie) {
        if (!checkDuplicatedMoviesOnUpdate(movie)){
            return Optional.of(movieRepo.save(movie));
        }
        return Optional.empty();
    }

    private Boolean checkDuplicatedMoviesOnUpdate (Movie movie){
        Movie currentMovie = findById(movie.getId()).get();
        if (currentMovie.getTitle().equalsIgnoreCase(movie.getTitle())){
            if (currentMovie.getDirector().getName().equalsIgnoreCase(movie.getDirector().getName())){
                if (currentMovie.getDirector().getSurnames().equalsIgnoreCase(movie.getDirector().getSurnames())) {
                    return false;
                }
            }
        }
        return checkDuplicatedMovies(movie);


    }


    private Boolean checkDuplicatedMovies (Movie movie){

        List<Movie> movies = movieRepo.findAllByTitleEqualsIgnoreCase(movie.getTitle());

        if (movies.size() == 0){
            return false;
        }

        for (Movie m:movies) {
            if (movie.getDirector().getName().equalsIgnoreCase(m.getDirector().getName())){
                if (movie.getDirector().getSurnames().equalsIgnoreCase(m.getDirector().getSurnames())) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Movie> movie = findById(id);
        if (movie.isEmpty()) return false;
        //Si la película tiene copias no se puede eliminar de la base de datos
        if (!movie.get().getCopies().isEmpty()) return false;

        movieRepo.deleteById(id);
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(movieRepo.findById(id).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAllByGenre(MovieGenre genero) {
        return movieRepo.findAllByGenre(genero);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAllByDirector(Director director) {
        return movieRepo.findAllByDirector(director);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findAllByActor(Actor actor) {
        return movieRepo.findAllByActors(actor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findByTitleContainsIgnoreCase(String title) {
        return movieRepo.findByTitleContainsIgnoreCase(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findMovieByDirector(String name, String surnames) {
        List<Director> directors = new ArrayList<Director>();
        List<Movie> movies = new ArrayList<Movie>();

        if (surnames.isEmpty()){
            directors= directorRepo.findByNameContainingIgnoreCase(name);
            directors.addAll(directorRepo.findBySurnamesContainingIgnoreCase(name));
        } else {
            directors=directorRepo.findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(name,surnames);
        }

        for (Director d:directors){
            movies.addAll(movieRepo.findAllByDirector(d));
        }
        return movies;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findMovieByActor(String name, String surnames) {
        List<Actor> actors = new ArrayList<Actor>();
        List<Movie> movies = new ArrayList<Movie>();

        if (surnames.isEmpty()){
            actors= actorRepo.findByNameContainingIgnoreCase(name);
            actors.addAll(actorRepo.findBySurnamesContainingIgnoreCase(name));
        } else {
            actors=actorRepo.findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(name,surnames);
        }

        for (Actor a:actors){
            movies.addAll(movieRepo.findAllByActors(a));
        }
        return movies;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findMovieByGenre(MovieGenre genre) {
        return movieRepo.findAllByGenre(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Movie> findMovieByLanguage(Language language) {
        return movieRepo.findAllByLanguage(language);
    }
}




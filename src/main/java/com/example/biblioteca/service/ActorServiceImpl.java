package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Actor;
import com.example.biblioteca.model.entity.Movie;
import com.example.biblioteca.model.entity.Musician;
import com.example.biblioteca.repository.IActorRepository;
import com.example.biblioteca.repository.IMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements IActorService {

    @Autowired
    private IActorRepository actorRepo;

    @Autowired
    private IMovieService movieService;


    @Override
    @Transactional(readOnly = true)
    public List<Actor> findAll() {
        return actorRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Actor> findAllByMovies(Movie movie) {
        return actorRepo.findAllByMovies(movie);
    }

    @Override
    public Optional<Actor> save(Actor actor) {
        if (!checkDuplicatedActor(actor)){
            return Optional.of(actorRepo.save(actor));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Actor> update(Actor actor) {
        if (!checkDuplicatedActor(actor)){
            return Optional.of(actorRepo.save(actor));
        }
        return Optional.empty();
    }

    private Boolean checkDuplicatedActor (Actor actor) {
        List<Actor> actors = actorRepo.findAllByNameEqualsIgnoreCaseAndSurnamesEqualsIgnoreCase(actor.getName(), actor.getSurnames());
        if (actors.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Actor> actor = findById(id);
        if (actor.isEmpty()) return false;
        //Si el actor tiene alguna película en la base de datos no se puede eliminar
        List<Movie> actorMovies = movieService.findAllByActor(actor.get());
        if (!actorMovies.isEmpty()) return false;
        actorRepo.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Actor> findById(Long id) {
        return Optional.ofNullable(actorRepo.findById(id)).orElse(null);
    }
}

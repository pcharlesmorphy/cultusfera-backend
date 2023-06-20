package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MovieDTO;
import com.example.biblioteca.model.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapperImpl implements IMovieMapper {

    @Autowired
    MovieGenreMapperImpl movieGenreMapper;
    @Autowired
    DirectorMapperImpl directorMapper;
    @Autowired
    ActorMapperImpl actorMapper;
    @Autowired
    LanguageMapperImpl languageMapper;

    @Autowired
    CopyMapperImpl copyMapper;

    @Autowired
    ReviewMapperImpl reviewMapper;

    @Override
    public Movie dtoToEntity(MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setId(movieDTO.getId());
        movie.setTitle(movieDTO.getTitle());
        movie.setPublishedYear(movieDTO.getPublishedYear());
        movie.setSynopsis(movieDTO.getSynopsis());
        movie.setDuration(movieDTO.getDuration());
        movie.setGenre(movieGenreMapper.dtoToEntity(movieDTO.getGenre()));
        movie.setDirector(directorMapper.dtoToEntity(movieDTO.getDirector()));
        movie.setActors(actorMapper.dtoListToEntity(movieDTO.getActors()));
        movie.setLanguage(languageMapper.dtoToEntity(movieDTO.getLanguage()));
        movie.setType(movieDTO.getType());
        if (movieDTO.getCopies() != null) {
            movie.setCopies(copyMapper.dtoListToEntity(movieDTO.getCopies()));
        }
        if (movieDTO.getReviews() != null){
            movie.setReviews(reviewMapper.dtoListToEntity(movieDTO.getReviews()));
        }
        movie.setRating(movieDTO.getRating());
        return movie;
    }

    @Override
    public MovieDTO entityToDto(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setPublishedYear(movie.getPublishedYear());
        movieDTO.setSynopsis(movie.getSynopsis());
        movieDTO.setDuration(movie.getDuration());
        movieDTO.setGenre(movieGenreMapper.entityToDto(movie.getGenre()));
        movieDTO.setDirector(directorMapper.entityToDto(movie.getDirector()));
        movieDTO.setActors(actorMapper.entityToDtoList(movie.getActors()));
        movieDTO.setLanguage(languageMapper.entityToDto(movie.getLanguage()));
        movieDTO.setType(movie.getType());
        if (movie.getCopies() != null){
            movieDTO.setCopies(copyMapper.entityToDtoList(movie.getCopies()));
        }
        if (movie.getReviews() != null){
            movieDTO.setReviews(reviewMapper.entityToDtoList(movie.getReviews()));
        }
        movieDTO.setRating(movie.getRating());
        return movieDTO;
    }

    @Override
    public List<MovieDTO> entityToDtoList(List<Movie> movies) {
        List<MovieDTO> moviesDTO = new ArrayList<MovieDTO>();
        for (Movie m: movies){
            moviesDTO.add(entityToDto(m));
        }
        return moviesDTO;
    }
}

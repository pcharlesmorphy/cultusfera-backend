package com.example.biblioteca.controller;

import com.example.biblioteca.model.dto.*;
import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.service.ActorServiceImpl;

import com.example.biblioteca.service.MovieGenreServiceImpl;

import com.example.biblioteca.service.MovieServiceImpl;
import com.example.biblioteca.service.mappers.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/peliculas")
public class MovieController {

    @Autowired
    private MovieGenreServiceImpl movieGenreService;

    @Autowired
    private MovieServiceImpl movieService;

    @Autowired
    private ActorServiceImpl actorService;

    @Autowired
    MovieGenreMapperImpl movieGenreMapper;
    @Autowired
    DirectorMapperImpl directorMapper;
    @Autowired
    ActorMapperImpl actorMapper;
    @Autowired
    LanguageMapperImpl languageMapper;
    @Autowired
    MovieMapperImpl movieMapper;

    @GetMapping()

    public ResponseEntity<List<MovieDTO>> findAllMovies(){
        return ResponseEntity.ok(movieMapper.entityToDtoList(movieService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<String> saveMovie(@RequestBody MovieDTO moviedto){
        try {
            Movie movie = movieMapper.dtoToEntity(moviedto);
            Optional<Movie> newMovie = movieService.save(movie);
            if (newMovie.isEmpty()) {
                String message = "La película con el mismo título y director ya existe";
                return ResponseEntity.badRequest().body(message);
            }
            return ResponseEntity.ok().build();
        }catch (DataAccessException e){
            String message = "Error en la base de datos";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e){
            String message = "Error en el servidor";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping()
    public ResponseEntity<String>updateMovie(@RequestBody MovieDTO movieDTO){
        try {
            if (movieService.findById(movieDTO.getId()).isEmpty()) {
                String message = ("La película solicitada no existe");
                return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
            }
            Movie movie = movieMapper.dtoToEntity(movieDTO);
            Optional<Movie> updatedMovie = movieService.update(movie);

            if (updatedMovie.isEmpty()) {
                String message = "La película con el mismo título y director ya existe";
                return ResponseEntity.badRequest().body(message);
            }
            return ResponseEntity.ok().build();
        }catch (DataAccessException e){
            String message = "Error en la base de datos";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e){
            String message = "Error en el servidor";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<String> deleteMovie(@PathVariable("id") Long id){

        if (!movieService.delete(id)){
            String message = "No se puede eliminar la película ya que tiene copias en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().build();
    }



    @GetMapping({"/{id}"})
    public ResponseEntity<MovieDTO> findMovieById (@PathVariable ("id") Long id){

        if (movieService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(movieMapper.entityToDto(movieService.findById(id).get()));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<MovieDTO>>  findMovieByTitle(@PathVariable ("titulo") String title){
        List<MovieDTO> movies = movieMapper.entityToDtoList(movieService.findByTitleContainsIgnoreCase(title));
        return ResponseEntity.ok(movies);
    }


    @GetMapping("/directores/")
    public ResponseEntity<List<MovieDTO>>  findMovieByDirector(
            @RequestParam (value="nombre") String name,
            @RequestParam (value="apellidos",required = false) String surnames){

        List<MovieDTO> movies = movieMapper.entityToDtoList(movieService.findMovieByDirector(name,surnames));
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/actores/")
    public ResponseEntity<List<MovieDTO>>  findMovieByActor(
            @RequestParam (value="nombre") String name,
            @RequestParam (value="apellidos",required = false) String surnames){

        List<MovieDTO> movies = movieMapper.entityToDtoList(movieService.findMovieByActor(name,surnames));
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/generos/")
    public ResponseEntity<List<MovieDTO>> findBookByGenre(
            @RequestParam (value="id") Long id,
            @RequestParam (value="nombre") String name){

        MovieGenre genre = new MovieGenre();
        genre.setId(id);
        genre.setName(name);
        List<MovieDTO> movies =movieMapper.entityToDtoList(movieService.findMovieByGenre(genre));
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/idiomas/")
    public ResponseEntity<List<MovieDTO>> findBookByLanguage(
            @RequestParam (value="id") Long id,
            @RequestParam (value="nombre") String name){

        Language language = new Language();
        language.setId(id);
        language.setName(name);
        List<MovieDTO> movies = movieMapper.entityToDtoList(movieService.findMovieByLanguage(language));
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/por-directores")
    public ResponseEntity<List<MovieDTO>> findAllByDirector(@RequestBody DirectorDTO directorDTO) {
        Director director = directorMapper.dtoToEntity(directorDTO);
        return ResponseEntity.ok(movieMapper.entityToDtoList(movieService.findAllByDirector(director)));
    }
    @GetMapping("/por-generos")
    public ResponseEntity<List<MovieDTO>> findAllByGenre(@RequestBody MovieGenreDTO genreDTO) {
        MovieGenre genero = movieGenreMapper.dtoToEntity(genreDTO);
        return ResponseEntity.ok(movieMapper.entityToDtoList(movieService.findAllByGenre(genero)));
    }

    @GetMapping("/por-interpretes")
    public ResponseEntity<List<MovieDTO>> findAllByActor(@RequestBody ActorDTO actorDTO) {
        Actor actor = actorMapper.dtoToEntity(actorDTO);
        return ResponseEntity.ok(movieMapper.entityToDtoList(movieService.findAllByActor(actor)));
    }

    @GetMapping({"/generos/{id}"})
    public ResponseEntity<Optional<MovieGenre>> findByIdGenre (@PathVariable("id") Long id){
        if (movieGenreService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(movieGenreService.findById(id));
    }

}

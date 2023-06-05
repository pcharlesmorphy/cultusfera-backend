package com.example.biblioteca.controller;

import com.example.biblioteca.model.dto.*;
import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.service.*;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("autores")
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorService;

    @Autowired
    private DirectorServiceImpl directorService;

    @Autowired
    private MusicianServiceImpl musicianService;

    @Autowired
    private WriterServiceImpl writerService;

    @Autowired
    private ActorServiceImpl actorService;

    @Autowired
    private AuthorMapperImpl authorMapper;

    @Autowired
    private WriterMapperImpl writerMapper;

    @Autowired
    private ActorMapperImpl actorMapper;

    @Autowired
    private DirectorMapperImpl directorMapper;

    @Autowired
    private MusicianMapperImpl musicianMapper;

    @GetMapping()

    public ResponseEntity<List<AuthorDTO>> findAll(){
        return ResponseEntity.ok(authorMapper.entityToDtoList(authorService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<AuthorDTO> save(@RequestBody AuthorDTO authorDTO){
        Author author = authorMapper.dtoToEntity(authorDTO);
        return new ResponseEntity<>(authorMapper.entityToDto(authorService.save(author)), HttpStatus.CREATED);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){

        if (!authorService.delete(id)){
            return new ResponseEntity<Boolean>(false,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(true);
    }

    @PutMapping()
    public ResponseEntity<AuthorDTO>update(@RequestBody AuthorDTO authorDTO){

        if(authorService.findById(authorDTO.getId()).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Author author = authorMapper.dtoToEntity(authorDTO);
        return ResponseEntity.ok().body(authorMapper.entityToDto(authorService.save(author)));
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<AuthorDTO> findById (@PathVariable ("id") Long id){
        if (authorService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(authorMapper.entityToDto(authorService.findById(id).get()));
    }

    @GetMapping("/{rol}")
    public ResponseEntity<List<Author>> findAllByRole(@PathVariable("rol") String rol){

        List<Author> listAutores = authorService.findAllByRole(rol);

        return ResponseEntity.ok(listAutores);
    }

    @GetMapping("/directores")

    public ResponseEntity<List<DirectorDTO>> findAllDirectors(){
        return ResponseEntity.ok(directorMapper.entityToDtoList(directorService.findAll()));
    }

    @PostMapping("/directores")
    public ResponseEntity<String> saveDirector(@RequestBody DirectorDTO directorDTO){
        try {
            Director director = directorMapper.dtoToEntity(directorDTO);
            Optional<Director> newDirector = directorService.save(director);

            if (newDirector.isEmpty()) {
                String message = "El director ya existe";
                return ResponseEntity.badRequest().body(message);
            }

            return ResponseEntity.ok().build();
        } catch (DataAccessException e){
            String message = "Error en la base de datos";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e){
            String message = "Error en el servidor";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping("/directores")
    public ResponseEntity<String>updateDirector(@RequestBody DirectorDTO directorDTO){
        try {
            if (directorService.findById(directorDTO.getId()).isEmpty()) {
                String message = ("La director solicitado no existe");
                return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
            }

            Director director = directorMapper.dtoToEntity(directorDTO);
            Optional<Director> updatedDirector = directorService.save(director);

            if (updatedDirector.isEmpty()) {
                String message = "El director ya existe";
                return ResponseEntity.badRequest().body(message);
            }

            return ResponseEntity.ok().build();
        } catch (DataAccessException e){
            String message = "Error en la base de datos";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e){
            String message = "Error en el servidor";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @DeleteMapping({"/directores/{id}"})
    public ResponseEntity<String> deleteDirector(@PathVariable("id") Long id){

        if (!directorService.delete(id)){
            String message = "No se puede eliminar el director ya que tiene películas en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().build();
    }



    @GetMapping({"directores/{id}"})
    public ResponseEntity<DirectorDTO> findDirectorById(@PathVariable ("id") Long id){

        if (directorService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(directorMapper.entityToDto(directorService.findById(id).get()));
    }


    @GetMapping("/escritores")

    public ResponseEntity<List<WriterDTO>> findAllWriters(){

        List<WriterDTO> escritores = new ArrayList<WriterDTO>();
        return ResponseEntity.ok(writerMapper.entityToDtoList(writerService.findAll()));
    }


    @PostMapping("/escritores")
    public ResponseEntity<String> saveWriter(@RequestBody WriterDTO writerDTO){
        try {
            Writer writer = writerMapper.dtoToEntity(writerDTO);
            Optional<Writer> newWriter = writerService.save(writer);
            if (newWriter.isEmpty()) {
                String message = "El escritor ya existe";
                return ResponseEntity.badRequest().body(message);
            }
            return ResponseEntity.ok().build();
        } catch (DataAccessException e){
            String message = "Error en la base de datos";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e){
            String message = "Error en el servidor";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping("/escritores")
    public ResponseEntity<String>updateWriter(@RequestBody WriterDTO writerDTO){
        try {
            if (writerService.findById(writerDTO.getId()).isEmpty()) {
                String message = ("El escritor solicitado no existe");
                return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
            }

            Writer writer = writerMapper.dtoToEntity(writerDTO);
            Optional<Writer> updatedWriter = writerService.save(writer);
            if (updatedWriter.isEmpty()) {
                String message = "El escritor ya existe";
                return ResponseEntity.badRequest().body(message);
            }
            return ResponseEntity.ok().build();
        } catch (DataAccessException e){
            String message = "Error en la base de datos";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e){
            String message = "Error en el servidor";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @DeleteMapping({"/escritores/{id}"})
    public ResponseEntity<String> deleteWriter(@PathVariable("id") Long id){

        if (!writerService.delete(id)){
            String message = "No se puede eliminar el escritor ya que tiene libros en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().build();
    }



    @GetMapping({"escritores/{id}"})
    public ResponseEntity<WriterDTO> findWriterById(@PathVariable ("id") Long id){

        if (writerService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(writerMapper.entityToDto(writerService.findById(id).get()));
    }



    @GetMapping("/musicos")

    public ResponseEntity<List<MusicianDTO>> findAllMusicians(){
        return ResponseEntity.ok(musicianMapper.entityToDtoList(musicianService.findAll()));
    }

    @PostMapping("/musicos")
    public ResponseEntity<String> saveMusician(@RequestBody MusicianDTO musicianDTO){
        try {
            Musician musician = musicianMapper.dtoToEntity(musicianDTO);
            Optional<Musician> newMusician = musicianService.save(musician);
            if (newMusician.isEmpty()) {
                String message = "El músico ya existe";
                return ResponseEntity.badRequest().body(message);
            }
            return ResponseEntity.ok().build();
        } catch (DataAccessException e){
            String message = "Error en la base de datos";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e){
            String message = "Error en el servidor";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @PutMapping("/musicos")
    public ResponseEntity<String>updateMusician(@RequestBody MusicianDTO musicianDTO){
        try {
            if (musicianService.findById(musicianDTO.getId()).isEmpty()) {
                String message = ("El músico solicitado no existe");
                return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
            }

            Musician musician = musicianMapper.dtoToEntity(musicianDTO);
            Optional<Musician> updateMusician = musicianService.update(musician);
            if (updateMusician.isEmpty()) {
                String message = "El músico ya existe";
                return ResponseEntity.badRequest().body(message);
            }
            return ResponseEntity.ok().build();
        } catch (DataAccessException e){
            String message = "Error en la base de datos";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e){
            String message = "Error en el servidor";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

    }

    @DeleteMapping({"/musicos/{id}"})
    public ResponseEntity<String> deleteMusician(@PathVariable("id") Long id){

        if (!musicianService.delete(id)){
            String message = "No se puede eliminar el músico ya que tiene albumes en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().build();
    }



    @GetMapping({"musicos/{id}"})
    public ResponseEntity<MusicianDTO> findMusicianById(@PathVariable ("id") Long id){

        if (musicianService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(musicianMapper.entityToDto(musicianService.findById(id).get()));
    }


    @GetMapping("/actores")

    public ResponseEntity<List<ActorDTO>> findAllActors(){
        return ResponseEntity.ok(actorMapper.entityToDtoList(actorService.findAll()));
    }

    @PostMapping("/actores")
    public ResponseEntity<String> saveActor(@RequestBody ActorDTO actorDTO){
        try {
            Actor actor = actorMapper.dtoToEntity(actorDTO);
            Optional<Actor> newActor = actorService.save(actor);

            if (newActor.isEmpty()) {
                String message = "El actor ya existe";
                return ResponseEntity.badRequest().body(message);
            }
            return ResponseEntity.ok().build();
        } catch (DataAccessException e){
            String message = "Error en la base de datos";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e){
            String message = "Error en el servidor";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }


    @PutMapping("/actores")
    public ResponseEntity<String>updateActor(@RequestBody ActorDTO actorDTO){
        try {
            if (actorService.findById(actorDTO.getId()).isEmpty()) {
                String message = ("El actor solicitado no existe");
                return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
            }

            Actor actor = actorMapper.dtoToEntity(actorDTO);
            Optional<Actor> updatedActor = actorService.save(actor);

            if (updatedActor.isEmpty()) {
                String message = "El actor ya existe";
                return ResponseEntity.badRequest().body(message);
            }
            return ResponseEntity.ok().build();
        } catch (DataAccessException e){
            String message = "Error en la base de datos";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        } catch (Exception e){
            String message = "Error en el servidor";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }
    }

    @DeleteMapping({"/actores/{id}"})
    public ResponseEntity<String> deleteActor(@PathVariable("id") Long id){

        if (!actorService.delete(id)){
            String message = "No se puede eliminar el actor ya que tiene películas en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping({"actores/{id}"})
    public ResponseEntity<ActorDTO> findActorById(@PathVariable ("id") Long id){

        if (actorService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(actorMapper.entityToDto(actorService.findById(id).get()));
    }

}

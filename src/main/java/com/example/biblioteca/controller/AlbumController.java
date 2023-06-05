package com.example.biblioteca.controller;

import com.example.biblioteca.model.dto.AlbumDTO;
import com.example.biblioteca.model.dto.BookDTO;
import com.example.biblioteca.model.dto.MusicGenreDTO;
import com.example.biblioteca.model.dto.RecordCompanyDTO;
import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.service.AlbumServiceImpl;
import com.example.biblioteca.service.RecordCompanyServiceImpl;
import com.example.biblioteca.service.MusicGenreServiceImpl;
import com.example.biblioteca.service.mappers.AlbumMapperImpl;
import com.example.biblioteca.service.mappers.MusicGenreMapperImpl;
import com.example.biblioteca.service.mappers.RecordCompanyMapperImpl;
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
@RequestMapping("/albumes")
public class AlbumController {

    @Autowired
    private RecordCompanyServiceImpl recordCompanyService;

    @Autowired
    private MusicGenreServiceImpl musicGenreService;

    @Autowired
    private AlbumServiceImpl albumService;

    @Autowired
    private AlbumMapperImpl albumMapper;

    @Autowired
    private RecordCompanyMapperImpl recordCompanyMapper;

    @Autowired
    private MusicGenreMapperImpl musicGenreMapper;


    @GetMapping()
    public ResponseEntity<List<AlbumDTO>> findAllAlbums(){
        return ResponseEntity.ok(albumMapper.entityToDtoList(albumService.findAll()));
    }


    @PostMapping()
    public ResponseEntity<String> saveAlbum(@RequestBody AlbumDTO albumDTO){
        try {
            Album album = albumMapper.dtoToEntity(albumDTO);
            Optional<Album> newAlbum = albumService.save(album);
            if (newAlbum.isEmpty()) {
                String message = "El album con mismo título, autor y discográfica ya existe";
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
    public ResponseEntity<String>updateAlbum(@RequestBody AlbumDTO albumDTO){
        try {
            if (albumService.findById(albumDTO.getId()).isEmpty()) {
                String message = ("El album solicitado no existe");
                return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
            }

            Album album = albumMapper.dtoToEntity(albumDTO);
            Optional<Album> updatedAlbum = albumService.update(album);

            if (updatedAlbum.isEmpty()) {
                String message = "El album con mismo título, autor y discográfica ya existe";
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
    public ResponseEntity<String> deleteAlbum(@PathVariable("id") Long id){

        if (!albumService.delete(id)){
            String message = "No se puede eliminar el álbum ya que tiene copias en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().build();
    }




    @GetMapping({"/{id}"})
    public ResponseEntity<AlbumDTO> findAlbumById (@PathVariable ("id") Long id){
        if (albumService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(albumMapper.entityToDto(albumService.findById(id).get()));
    }


    @GetMapping("/por-autores")
    public ResponseEntity<List<Album>> findAllByMusician(@RequestBody Musician musician) {
        return ResponseEntity.ok(albumService.findAllByMusician(musician));
    }

    @GetMapping("/por-generos")
    public ResponseEntity<List<Album>> findAllByGenre(@RequestBody MusicGenre genre) {
        return ResponseEntity.ok(albumService.findAllByGenre(genre));
    }


    @GetMapping("/discograficas")
    public ResponseEntity<List<RecordCompanyDTO>> findAllRecordCompanies(){
        return ResponseEntity.ok(recordCompanyMapper.entityToDtoList(recordCompanyService.findAll()));
    }


    @PostMapping("/discograficas")
    public ResponseEntity<String> saveRecordCompany(@RequestBody RecordCompanyDTO recordCompanyDTO){
        try {
            RecordCompany recordCompany = recordCompanyMapper.dtoToEntity(recordCompanyDTO);
            Optional<RecordCompany> newCompany = recordCompanyService.save(recordCompany);
            if (newCompany.isEmpty()) {
                String message = "La discográfica ya existe";
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

    @PutMapping("/discograficas")
    public ResponseEntity<String>updateRecordCompany(@RequestBody RecordCompanyDTO recordCompanyDTO){
        try {
            if (recordCompanyService.findById(recordCompanyDTO.getId()).isEmpty()) {
                String message = ("La discográfica solicitada no existe");
                return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
            }
            RecordCompany recordCompany = recordCompanyMapper.dtoToEntity(recordCompanyDTO);
            Optional<RecordCompany> updatedCompany = recordCompanyService.update(recordCompany);
            if (updatedCompany.isEmpty()) {
                String message = "La discográfica ya existe";
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

    @DeleteMapping({"/discograficas/{id}"})
    public ResponseEntity<String> deleteRecordCompany(@PathVariable("id") Long id){

        if (!recordCompanyService.delete(id)){
            String message = "No se puede eliminar la discográfica ya que tiene albumes en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }

        return ResponseEntity.ok().build();
    }



    @GetMapping({"/discograficas/{id}"})
    public ResponseEntity<RecordCompanyDTO> findByIdRecordCompany (@PathVariable ("id") Long id){
        if (recordCompanyService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(recordCompanyMapper.entityToDto(recordCompanyService.findById(id).get()));
    }

    @GetMapping("/generos")
    public ResponseEntity<List<MusicGenreDTO>> findAllGenres(){
        return ResponseEntity.ok(musicGenreMapper.entityToDtoList(musicGenreService.findAll()));
    }

    @GetMapping({"/generos/{id}"})
    public ResponseEntity<Optional<MusicGenre>> findByIdGenre (@PathVariable ("id") Long id){
        if (musicGenreService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(musicGenreService.findById(id));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<AlbumDTO>>  findBookByTitle(@PathVariable ("titulo") String title){
        List<AlbumDTO> albums = albumMapper.entityToDtoList(albumService.findByTitleContainsIgnoreCase(title));
        return ResponseEntity.ok(albums);
    }


    @GetMapping("/musicos/")
    public ResponseEntity<List<AlbumDTO>>  findBookByWriter(
            @RequestParam (value="nombre") String name,
            @RequestParam (value="apellidos",required = false) String surnames){

        List<AlbumDTO> albums = albumMapper.entityToDtoList(albumService.findAlbumByMusician(name, surnames));
        return ResponseEntity.ok(albums);
    }


    @GetMapping("/generos/")
    public ResponseEntity<List<AlbumDTO>> findBookByGenre(
            @RequestParam (value="id") Long id,
            @RequestParam (value="nombre") String name){

        MusicGenre genre = new MusicGenre();
        genre.setId(id);
        genre.setName(name);
        List<AlbumDTO> albums = albumMapper.entityToDtoList(albumService.findAlbumByGenre(genre));
        return ResponseEntity.ok(albums);
    }



}

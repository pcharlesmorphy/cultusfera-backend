package com.example.biblioteca.controller;

import com.example.biblioteca.model.dto.BookDTO;
import com.example.biblioteca.model.dto.MagazineDTO;
import com.example.biblioteca.model.dto.MagazinePublisherDTO;
import com.example.biblioteca.model.dto.MagazineSubjectDTO;
import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.service.MagazinePublisherServiceImpl;
import com.example.biblioteca.service.MagazineServiceImpl;
import com.example.biblioteca.service.MagazineSubjectServiceImpl;
import com.example.biblioteca.service.mappers.MagazineMapperImpl;
import com.example.biblioteca.service.mappers.MagazinePublisherMapperImpl;
import com.example.biblioteca.service.mappers.MagazineSubjectMappeImpl;
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
@RequestMapping("/revistas")
public class MagazineController {
    @Autowired
    private MagazinePublisherServiceImpl magazinePublisherService;

    @Autowired
    private MagazineSubjectServiceImpl magazineSubjectService;

    @Autowired
    private MagazineServiceImpl magazineService;

    @Autowired
    private MagazineMapperImpl magazineMapper;

    @Autowired
    private MagazinePublisherMapperImpl magazinePublisherMapper;

    @Autowired
    private MagazineSubjectMappeImpl magazineSubjectMapper;



    @GetMapping()
    public ResponseEntity<List<MagazineDTO>> findAllMagazines(){
        return ResponseEntity.ok(magazineMapper.entityToDtoList(magazineService.findAll()));
    }

    @PostMapping()
    public ResponseEntity<String> saveMagazine(@RequestBody MagazineDTO magazineDTO){
        try {
            Magazine magazine = magazineMapper.dtoToEntity(magazineDTO);
            Optional<Magazine> newMagazine = magazineService.save(magazine);
            if (newMagazine.isEmpty()) {
                String message = "La revista con el mismo título y número de ejemplar ya existe";
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
    public ResponseEntity<String>updateMagazine(@RequestBody MagazineDTO magazineDTO){
        try {
            if (magazineService.findById(magazineDTO.getId()).isEmpty()) {
                String message = ("La revista solicitada no existe");
                return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
            }
            Magazine magazine = magazineMapper.dtoToEntity(magazineDTO);
            Optional<Magazine> updatedMagazine = magazineService.update(magazine);
            if (updatedMagazine.isEmpty()) {
                String message = "La revista con el mismo título y número de ejemplar ya existe";
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
    public ResponseEntity<String> deleteMagazine(@PathVariable("id") Long id){

        if (!magazineService.delete(id)){
            String message = "No se puede eliminar la revista ya que tiene copias en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().build();
    }


    @GetMapping({"/{id}"})
    public ResponseEntity<MagazineDTO> findMagazineById (@PathVariable ("id") Long id){

        if (magazineService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(magazineMapper.entityToDto(magazineService.findById(id).get()));
    }

    @GetMapping("/editoriales")
    public ResponseEntity<List<MagazinePublisherDTO>> findAllPublishers(){
        return ResponseEntity.ok(magazinePublisherMapper.entityToDtoList(magazinePublisherService.findAll()));
    }

    @PostMapping("/editoriales")
    public ResponseEntity<String> savePublisher(@RequestBody MagazinePublisherDTO publisherDTO){
        try {
            MagazinePublisher publisher = magazinePublisherMapper.dtoToEntity(publisherDTO);
            Optional<MagazinePublisher> newPublisher = magazinePublisherService.save(publisher);
            if (newPublisher.isEmpty()) {
                String message = "La editorial ya existe";
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

    @PutMapping("/editoriales")
    public ResponseEntity<String>updatePublisher(@RequestBody MagazinePublisherDTO publisherDTO){
        try {
            if (magazinePublisherService.findById(publisherDTO.getId()).isEmpty()) {
                String message = ("La editorial solicitada no existe");
                return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
            }

            MagazinePublisher publisher = magazinePublisherMapper.dtoToEntity(publisherDTO);
            Optional<MagazinePublisher> updatedPublisher = magazinePublisherService.update(publisher);
            if (updatedPublisher.isEmpty()) {
                String message = "La editorial ya existe";
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

    @DeleteMapping({"/editoriales/{id}"})
    public ResponseEntity<String> deletePublisher(@PathVariable("id") Long id){

        if (!magazinePublisherService.delete(id)){
            String message = "No se puede eliminar la editorial ya que tiene revistas en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().build();
    }



    @GetMapping({"/editoriales/{id}"})
    public ResponseEntity<MagazinePublisherDTO> findByIdPublisher (@PathVariable ("id") Long id){

        if (magazinePublisherService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(magazinePublisherMapper.entityToDto(magazinePublisherService.findById(id).get()));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<MagazineDTO>>  findMagazineByTitle(@PathVariable ("titulo") String title){
        List<MagazineDTO> magazines = magazineMapper.entityToDtoList(magazineService.findByTitleContainsIgnoreCase(title));
        return ResponseEntity.ok(magazines);
    }

    @GetMapping("/temas/")
    public ResponseEntity<List<MagazineDTO>> findBookBySubject(
            @RequestParam (value="id") Long id,
            @RequestParam (value="nombre") String name){

        MagazineSubject subject = new MagazineSubject();
        subject.setId(id);
        subject.setName(name);
        List<MagazineDTO> magazines = magazineMapper.entityToDtoList(magazineService.findBySubject(subject));
        return ResponseEntity.ok(magazines);
    }

    @GetMapping("/idiomas/")
    public ResponseEntity<List<MagazineDTO>> findBookByLanguage(
            @RequestParam (value="id") Long id,
            @RequestParam (value="nombre") String name){

        Language language = new Language();
        language.setId(id);
        language.setName(name);
        List<MagazineDTO> magazines = magazineMapper.entityToDtoList(magazineService.findByLanguage(language));
        return ResponseEntity.ok(magazines);
    }
}

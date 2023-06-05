package com.example.biblioteca.controller;

import com.example.biblioteca.model.dto.BookPublisherDTO;
import com.example.biblioteca.model.dto.BookDTO;
import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.service.BookPublisherServiceImpl;
import com.example.biblioteca.service.LiteraryGenreServiceImpl;
import com.example.biblioteca.service.BookServiceImpl;
import com.example.biblioteca.service.ResourceServiceImpl;
import com.example.biblioteca.service.mappers.AuthorMapperImpl;
import com.example.biblioteca.service.mappers.BookPublisherMapperImpl;
import com.example.biblioteca.service.mappers.BookMapperImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
@Log4j2
@RestController
@Tag(name="Libros",description="API Libros")

@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/libros")
public class BookController {

    @Autowired
    private BookPublisherServiceImpl bookPublisherService;
    @Autowired
    private LiteraryGenreServiceImpl literaryGenreService;

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private ResourceServiceImpl resourceService;

    @Autowired
    private BookMapperImpl bookMapper;

    @Autowired
    private BookPublisherMapperImpl bookPublisherMapper;

    @Autowired
    private AuthorMapperImpl authorMapper;

    @GetMapping
    public ResponseEntity<List<BookDTO>>  findAllBooks(){
        return ResponseEntity.ok(bookMapper.entityToDtoList(bookService.findAll()));
    }

    @PostMapping

    public ResponseEntity<String> saveBook(@RequestBody BookDTO bookDTO){
        Book book = bookMapper.dtoToEntity(bookDTO);
        Optional<Book> newBook = bookService.save(book);
        try{
            if (newBook.isEmpty()){
                String message = "El libro con el mismo título, autor y editorial ya existe";
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

    @PutMapping()
    public ResponseEntity<String>updateBook(@RequestBody BookDTO bookDTO){

            try {
                if (bookService.findById(bookDTO.getId()).isEmpty()) {
                    String message = ("El libro solicitado no existe");
                    return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
                }
                Book book = bookMapper.dtoToEntity(bookDTO);
                Optional<Book> updatedBook = bookService.update(book);

                if (updatedBook.isEmpty()){
                    String message = "El libro con el mismo título, autor y editorial ya existe";
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
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id){

        if (!bookService.delete(id)){
            String message = "No se puede eliminar el libro ya que tiene copias en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }
        return ResponseEntity.ok().build();
    }



    @GetMapping({"/{id}"})
    public ResponseEntity<BookDTO> findBookById(@PathVariable ("id") Long id){

        if (bookService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bookMapper.entityToDto(bookService.findById(id).get()));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<BookDTO>>  findBookByTitle(@PathVariable ("titulo") String title){
        List<BookDTO> books = bookMapper.entityToDtoList(bookService.findByTitleContainsIgnoreCase(title));
        return ResponseEntity.ok(books);
    }


    @GetMapping("/autores/")
    public ResponseEntity<List<BookDTO>>  findBookByWriter(
            @RequestParam (value="nombre") String name,
            @RequestParam (value="apellidos",required = false) String surnames){

        List<BookDTO> books = bookMapper.entityToDtoList(bookService.findBookByWriter(name, surnames));
        return ResponseEntity.ok(books);
    }


    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<List<BookDTO>>  findBookByIsbn(@PathVariable ("isbn") String isbn){
        List<BookDTO> books = bookMapper.entityToDtoList(bookService.findByIsbnContainsIgnoreCase(isbn));
        return ResponseEntity.ok(books);
    }

    @GetMapping("/generos/")
    public ResponseEntity<List<BookDTO>> findBookByGenre(
            @RequestParam (value="id") Long id,
            @RequestParam (value="nombre") String name){

        LiteraryGenre genre = new LiteraryGenre();
        genre.setId(id);
        genre.setName(name);
        List<BookDTO> books = bookMapper.entityToDtoList(bookService.findBookByGenre(genre));
        return ResponseEntity.ok(books);
    }

    @GetMapping("/idiomas/")
    public ResponseEntity<List<BookDTO>> findBookByLanguage(
            @RequestParam (value="id") Long id,
            @RequestParam (value="nombre") String name){

        Language language = new Language();
        language.setId(id);
        language.setName(name);
        List<BookDTO> books = bookMapper.entityToDtoList(bookService.findBookByLanguage(language));
        return ResponseEntity.ok(books);
    }

    @GetMapping("/editoriales")
    public ResponseEntity<List<BookPublisherDTO>> findAllPublishers(){

        List<BookPublisher> publishers = bookPublisherService.findAll();
        return ResponseEntity.ok(bookPublisherMapper.entityToDtoList(publishers));
    }

    @PostMapping("/editoriales")
    public ResponseEntity<String> savePublisher(@RequestBody BookPublisherDTO publisherDTO){
        try {
            BookPublisher publisher = bookPublisherMapper.dtoToEntity(publisherDTO);
            Optional<BookPublisher> newPublisher = bookPublisherService.save(publisher);
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
    public ResponseEntity<String>updatePublisher(@RequestBody BookPublisherDTO publisherDTO){
        try {
            if (bookPublisherService.findById(publisherDTO.getId()).isEmpty()) {
                String message = ("La editorial solicitada no existe");
                return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
            }
            BookPublisher publisher = bookPublisherMapper.dtoToEntity(publisherDTO);
            Optional<BookPublisher> updatedPublisher = bookPublisherService.update(publisher);
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

        if (!bookPublisherService.delete(id)){
            String message = "No se puede eliminar la editorial ya que tiene libros en la base de datos";
            return ResponseEntity.badRequest().body(message);
        }

        return ResponseEntity.ok().build();
    }



    @GetMapping({"/editoriales/{id}"})
    public ResponseEntity<BookPublisherDTO> findByIdPublisher (@PathVariable ("id") Long id){
        if (bookPublisherService.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(bookPublisherMapper.entityToDto(bookPublisherService.findById(id).get()));
    }
}

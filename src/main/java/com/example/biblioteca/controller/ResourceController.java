package com.example.biblioteca.controller;


import com.example.biblioteca.model.dto.*;
import com.example.biblioteca.model.dto.frontend.FrontendTransactionDTO;
import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.service.CopyServiceImpl;
import com.example.biblioteca.service.ResourceServiceImpl;
import com.example.biblioteca.service.ReviewServiceImpl;
import com.example.biblioteca.service.TransactionServiceImpl;
import com.example.biblioteca.service.mappers.*;
import com.example.biblioteca.service.mappers.frontend.IFrontendTransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/recursos")
public class ResourceController {

    @Autowired
    ResourceServiceImpl resourceService;

    @Autowired
    ResourceMapperImpl resourceMapper;

    @Autowired
    BookMapperImpl bookMapper;

    @Autowired
    MovieMapperImpl movieMapper;

    @Autowired
    AlbumMapperImpl albumMapper;

    @Autowired
    MagazineMapperImpl magazineMapper;
    @Autowired
    CopyServiceImpl copyService;
    @Autowired
    CopyMapperImpl copyMapper;

    @Autowired
    TransactionServiceImpl transactionService;

    @Autowired
    ITransactionMapper transactionMapper;

    @Autowired
    ReviewMapperImpl reviewMapper;

    @Autowired
    IFrontendTransactionMapper frontendTransactionMapper;

    @Autowired
    ReviewServiceImpl reviewService;

    @GetMapping
    public ResponseEntity<List<ResourceDTO>> findAllResources(){

        List<Resource> resources = resourceService.findAll();
        List<ResourceDTO> resourcesDTO = convertResourcesElementsToDTO(resources);
        return ResponseEntity.ok(resourcesDTO);
    }


    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<ResourceDTO>>  findResourceByTitle(@PathVariable("titulo") String title){

        List<Resource> resources = resourceService.findAllByTitleContainsIgnoreCase(title);
        List<ResourceDTO> resourcesDTO = convertResourcesElementsToDTO(resources);
        return ResponseEntity.ok(resourcesDTO);
    }

    List<ResourceDTO> convertResourcesElementsToDTO (List<Resource>resources){

        List<ResourceDTO> resourcesDTO = new ArrayList<ResourceDTO>();

        for (Resource r:resources) {
            if (r instanceof Book) {
                BookDTO bookDTO = bookMapper.entityToDto((Book)r);
                resourcesDTO.add(bookDTO);
            } else if (r instanceof Movie) {
                MovieDTO movieDTO = movieMapper.entityToDto((Movie) r);
                resourcesDTO.add(movieDTO);
            } else if (r instanceof Album) {
                AlbumDTO albumDTO= albumMapper.entityToDto((Album) r);
                resourcesDTO.add(albumDTO);
            } else if (r instanceof Magazine) {
                MagazineDTO magazineDTO = magazineMapper.entityToDto((Magazine) r);
                resourcesDTO.add(magazineDTO);
            }
        }

        return resourcesDTO;

    }


    @GetMapping({"/{id}"})
    public ResponseEntity<ResourceDTO> findBookById(@PathVariable ("id") Long id){

        if (resourceService.findResourceById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(resourceMapper.entityToDto(resourceService.findResourceById(id).get()));
    }

    @PostMapping("/copias")
    public ResponseEntity <CopyDTO> addCopy (@RequestBody CopyDTO copyDTO){

        Copy copy = copyService.save(copyMapper.dtoToEntity(copyDTO));
        return ResponseEntity.ok(copyMapper.entityToDto(copy));
    }

    @PutMapping("/copias")
    public ResponseEntity<CopyDTO>updateCopy(@RequestBody CopyDTO copyDTO){

        if(copyService.findCopyById(copyDTO.getId()).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        copyDTO = copyMapper.entityToDto(copyService.update(copyMapper.dtoToEntity(copyDTO)));
        return ResponseEntity.ok().body(copyDTO);
    }

    @GetMapping("/copias/{id}")
    public ResponseEntity <List<CopyDTO>> getResourceCopies (@PathVariable ("id") Long id){

        Resource resource = resourceService.findResourceById(id).get();
        List<CopyDTO> copiesDTO = copyMapper.entityToDtoList(copyService.findAllResourceCopies(resource));

        return ResponseEntity.ok(copiesDTO);
    }

    @GetMapping("/copias")
    public ResponseEntity <List<CopyDTO>> getAllCopies(){
        return ResponseEntity.ok(copyMapper.entityToDtoList(copyService.findAll()));

    }

    @GetMapping("/copia/{id}")
    public ResponseEntity <CopyDTO> getCopyById (@PathVariable ("id") Long id){
        if (copyService.findCopyById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(copyMapper.entityToDto(copyService.findCopyById(id).get()));
    }

    @GetMapping("/historicos/{titulo}")
    public ResponseEntity <List<FrontendTransactionDTO>> getHistoricalByResource (@PathVariable ("titulo") String title){

        //Obtenemos los recursos que contengan en su título la palabra clave

        if ( resourceService.findAllByTitleContainsIgnoreCase(title).isEmpty()){
            ResponseEntity.notFound().build();
        }

        List<Resource> resources = resourceService.findAllByTitleContainsIgnoreCase(title);

        //Recorremos lista recursos y obtenemos la lista de copias de cada recurso

        List<Copy> copies = new ArrayList<Copy>();

        for (Resource r:resources){
            resourceService.classifyResource(r);
            copies.addAll(r.getCopies());
        }

        //De la lista de copias obtenemos el lista de transacciones
        List<Transaction> transactions = new ArrayList<Transaction>();
        for (Copy c:copies){
            transactions.addAll(copyService.findCopyHistoricTransactions(c));
        }

        return ResponseEntity.ok(frontendTransactionMapper.entityToDtoList(transactions));
    }

    @GetMapping("/prestamos/{titulo}")
    public ResponseEntity <List<FrontendTransactionDTO>> getActiveLoansByResource (@PathVariable ("titulo") String title){

        //Obtenemos los recursos que contengan en su título la palabra clave

        if ( resourceService.findAllByTitleContainsIgnoreCase(title).isEmpty()){
            ResponseEntity.notFound().build();
        }

        List<Resource> resources = resourceService.findAllByTitleContainsIgnoreCase(title);

        //Recorremos lista recursos y obtenemos la lista de copias de cada recurso

        List<Copy> copies = new ArrayList<Copy>();

        for (Resource r:resources){
            resourceService.classifyResource(r);
            copies.addAll(r.getCopies());
        }

        //De la lista de copias obtenemos el lista de transacciones activas
        List<Transaction> transactions = new ArrayList<Transaction>();
        for (Copy c:copies){
            transactions.addAll(copyService.findCopyActiveTransactions(c));
        }

        return ResponseEntity.ok(frontendTransactionMapper.entityToDtoList(transactions));
    }

    @PostMapping("/reviews")
    public ResponseEntity <ReviewDTO> addReview (@RequestBody ReviewDTO reviewDTO){

        Optional<Review> newReview = reviewService.save(reviewMapper.dtoToEntity(reviewDTO));

        if (newReview.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(reviewMapper.entityToDto(newReview.get()));
    }

    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<ReviewDTO>>  findAllReviews(@PathVariable("id") Long id){
        return ResponseEntity.ok(reviewMapper.entityToDtoList(reviewService.findAllByResource(id)));
    }

    @DeleteMapping({"/reviews/{id}"})
    public ResponseEntity<Boolean> deleteReview(@PathVariable("id") Long id){

        if (!reviewService.delete(id)){
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(true);
    }

    @PutMapping("/reviews")
    public ResponseEntity<ReviewDTO>updateReview(@RequestBody ReviewDTO reviewDTO){

        if(reviewService.findById(reviewDTO.getId()).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Optional<Review> updatedReview = reviewService.update(reviewMapper.dtoToEntity(reviewDTO));

        if (updatedReview.isEmpty()){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(reviewMapper.entityToDto(updatedReview.get()));
    }


}

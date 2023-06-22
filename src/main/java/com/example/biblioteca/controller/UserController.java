package com.example.biblioteca.controller;

import com.example.biblioteca.model.dto.*;
import com.example.biblioteca.model.dto.frontend.FrontendTransactionDTO;
import com.example.biblioteca.model.dto.frontend.FrontendUserDTO;
import com.example.biblioteca.model.entity.Loan;
import com.example.biblioteca.model.entity.Resource;
import com.example.biblioteca.model.entity.Transaction;
import com.example.biblioteca.model.entity.User;
import com.example.biblioteca.service.*;
import com.example.biblioteca.service.mappers.*;
import com.example.biblioteca.service.mappers.frontend.IFrontendTransactionMapper;
import com.example.biblioteca.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="http://localhost:4200")
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserMapperImpl userMapper;

    @Autowired
    ResourceServiceImpl resourceService;

    @Autowired
    TransactionServiceImpl transactionService;

    @Autowired
    TransactionMapperImpl transactionMapper;

    @Autowired
    IFrontendTransactionMapper frontendTransactionMapper;

    @Autowired
    IFrontendUserMapper frontendUserMapper;

    @Autowired
    ReviewMapperImpl reviewMapper;

    @Autowired
    ReviewServiceImpl reviewService;

    @Autowired
    PenaltyServiceImpl penaltyService;

    @Autowired
    PenaltyMapperImpl penaltyMapper;

    @PostMapping
    public ResponseEntity createUser (@RequestBody UserDTO userDTO){

        // Encode user password
        String plainUserPassword = userDTO.getPassword();
        String encodedUserPassword = Utils.encodeUserPassword(plainUserPassword);
        userDTO.setPassword(encodedUserPassword);

        User user = userMapper.dtoToEntity(userDTO);
        userService.save(user);

        return ResponseEntity.ok().build();
    }


    @GetMapping("/login")
    public ResponseEntity <UserDTO> loginUser (@RequestParam(value = "username", required = true) String username, @RequestParam(value = "password", required = true) String password) {

            Optional<User> currentUser = userService.findUserByUsername(username);

            if(currentUser.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            String userPassword = Utils.decodeUserPassword((currentUser.get().getPassword()));

            if (!userPassword.equals(password)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            currentUser.get().setPassword("");
            return ResponseEntity.ok(userMapper.entityToDto(currentUser.get()));

    }

    @GetMapping
    public ResponseEntity<List<FrontendUserDTO>> findAllUsers(){
        return ResponseEntity.ok(frontendUserMapper.entityToDtoList(userService.findAll()));
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<FrontendUserDTO>> findAllClientUsers(){
        return ResponseEntity.ok(frontendUserMapper.entityToDtoList(userService.findClientUsers()));
    }

    @PutMapping
    public ResponseEntity<UserDTO>updateUser(@RequestBody UserDTO userDTO){

        if(userService.findById(userDTO.getId()).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        userDTO = userMapper.entityToDto(userService.update(userMapper.dtoToEntity(userDTO)));
        return ResponseEntity.ok().body(userDTO);
    }


    @PatchMapping
    public ResponseEntity partialUpdateUser(@RequestBody UserDTO userDTO){

       Optional<User> user = userService.findById(userDTO.getId());

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        user.get().setAddress(userDTO.getAddress());
        user.get().setEmail(userDTO.getEmail());
        user.get().setPhone(userDTO.getPhone());

        userService.update(user.get());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/password")
    public ResponseEntity updateUserPassword(@RequestBody UserDTO userDTO){

        Optional<User> user = userService.findById(userDTO.getId());

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        user.get().setPassword(Utils.encodeUserPassword(userDTO.getPassword()));

        userService.update(user.get());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/historicos/")
    public ResponseEntity <List<FrontendTransactionDTO>> getHistoricalByUser (@RequestParam ("name") String name,@RequestParam("surnames") String surnames){

        //Conseguimos el usuario que nos han introducido

        if (userService.findByName(name,surnames).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = userService.findByName(name,surnames).get();

        //Obtenemos el listado de transacciones del usuario

        List<Transaction> transactions = transactionService.getCompletedLoansByUser(user);

        for (Transaction t:transactions){
            resourceService.classifyResource(t.getCopy().getResource());
        }

        return ResponseEntity.ok(frontendTransactionMapper.entityToDtoList(transactions));
    }

    @GetMapping("/historicos/username/{username}")
    public ResponseEntity <List<FrontendTransactionDTO>> getHistoricalByUsername (@PathVariable ("username") String username){

        //Conseguimos el usuario que nos han introducido

        if (userService.findUserByUsername(username).isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = userService.findUserByUsername(username).get();

        //Obtenemos el listado de transacciones del usuario

        List<Transaction> transactions = transactionService.getCompletedLoansByUser(user);
        for (Transaction t:transactions){
            resourceService.classifyResource(t.getCopy().getResource());
        }

        return ResponseEntity.ok(frontendTransactionMapper.entityToDtoList(transactions));
    }


    @GetMapping("/reviews/{id}")
    public ResponseEntity<List<ReviewDTO>>  findAllReviews(@PathVariable("id") Long id){
        return ResponseEntity.ok(reviewMapper.entityToDtoList(reviewService.findAllByUser(id)));
    }



    @GetMapping({"/{id}"})
    public ResponseEntity<FrontendUserDTO> findUserById(@PathVariable ("id") Long id){
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(frontendUserMapper.entityToDto(userService.findById(id).get()));
    }

    @GetMapping("/sanciones/{id}")
    public ResponseEntity<List<PenaltyDTO>> findPenaltyByUser(@PathVariable ("id") Long id){
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        penaltyService.findByUser(user.get());
        return ResponseEntity.ok(penaltyMapper.entityToDtoList(penaltyService.findByUser(user.get())));
    }

    @PostMapping("/sanciones")
    public ResponseEntity addUserPenalty (@RequestBody PenaltyDTO penalty){
        penaltyService.save(penaltyMapper.dtoToEntity(penalty));
        return ResponseEntity.ok().build();
    }
}



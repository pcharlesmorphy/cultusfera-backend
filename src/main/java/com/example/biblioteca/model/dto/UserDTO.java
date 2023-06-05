package com.example.biblioteca.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String surnames;
    private String email;
    private String nif;
    private String address;
    private String phone;
    private String username;
    private String password;
    private LocalDate registrationDate;
    private UserRoleDTO role;
    private List<TransactionDTO> transactions;

}

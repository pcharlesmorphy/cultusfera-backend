package com.example.biblioteca.model.dto.frontend;

import com.example.biblioteca.model.dto.TransactionDTO;
import com.example.biblioteca.model.dto.UserRoleDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FrontendUserDTO {
    private Long id;
    private String name;
    private String surnames;
    private String email;
    private String nif;
    private String address;
    private String phone;
    private String username;
    private LocalDate registrationDate;
    private UserRoleDTO role;
    private List<TransactionDTO> transactions;
}

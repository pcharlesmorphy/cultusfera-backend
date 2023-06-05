package com.example.biblioteca.model.dto;

import com.example.biblioteca.model.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class UserRoleDTO {

    private Long id;
    private String type;

}

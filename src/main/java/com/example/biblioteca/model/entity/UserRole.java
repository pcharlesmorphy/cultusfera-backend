package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="type",nullable=false,length=20)
    private String type;

    @OneToMany(mappedBy = "role",cascade = CascadeType.MERGE)
    @JsonBackReference
    private List<User> users;
}

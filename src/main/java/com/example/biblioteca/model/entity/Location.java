package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name="name",nullable=false,length=30)
    private String name;
    @Column(name="address",nullable=false,length=45)
    private String address;
    @Column(name="phone",nullable=false,length=15)
    private String phone;

    @OneToMany(mappedBy = "location",cascade = CascadeType.MERGE)
    @JsonBackReference
    private List<Copy> copies;
}

package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class RecordCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",unique = true,nullable=false,length=30)
    private String name;

    @OneToMany(mappedBy = "recordCompany",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Album> albums;
}

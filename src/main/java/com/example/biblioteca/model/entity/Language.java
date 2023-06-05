package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",unique = true,nullable=false,length=20)
    private String name;

    @OneToMany(mappedBy = "language",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Movie> movies;

    @OneToMany(mappedBy = "language",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Book> books;

    @OneToMany(mappedBy = "language",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Magazine> magazines;

}

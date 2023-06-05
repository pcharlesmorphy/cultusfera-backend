package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class LiteraryGenre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",unique = true,nullable=false,length=20)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "genre",cascade = CascadeType.ALL)
    private List<Book> books;

}

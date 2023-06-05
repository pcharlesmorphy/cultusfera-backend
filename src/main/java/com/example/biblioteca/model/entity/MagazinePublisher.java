package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity

public class MagazinePublisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",unique = true,nullable=false,length=25)
    private String name;

    @OneToMany(mappedBy = "publisher",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Magazine> magazines;

}

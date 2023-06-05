package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Month {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",unique = true,nullable=false,length=20)
    private String name;

    @OneToMany(mappedBy = "month",cascade = CascadeType.MERGE)
    private List<Magazine> magazines;

}

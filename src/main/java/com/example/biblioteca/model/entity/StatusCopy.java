package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Data
public class StatusCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @Column(name="type",unique = true,nullable=false,length=20)
    String status;

    @OneToMany(mappedBy = "status",cascade = CascadeType.MERGE)
    @JsonBackReference
    @ToString.Exclude
    private List<Copy> copies;

}

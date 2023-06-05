package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class TransactionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type",unique = true,nullable=false,length=30)
    private String type;

    @JsonBackReference
    @OneToMany(mappedBy = "status",cascade = CascadeType.MERGE)
    private List<Transaction> transactions;
}

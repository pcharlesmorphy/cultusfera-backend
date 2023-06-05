package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="startDate",nullable=false)
    private LocalDate startDate;
    @Column(name="endDate",nullable=false)
    private LocalDate endDate;
    @Column(name="status",nullable=false)
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idReason")
    @JsonManagedReference
    private PenaltyReason reason;

    @Lob
    @Column(name="comments",columnDefinition = "LONGTEXT",nullable=true)
    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUser")
    @JsonManagedReference
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idLoan")
    @JsonManagedReference
    private Loan loan;

}

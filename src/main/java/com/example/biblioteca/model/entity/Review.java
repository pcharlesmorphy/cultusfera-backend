package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity

public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="date",nullable=false)
    private LocalDate date;
    @Column(name="title",nullable=false,length = 40)
    public String title;
    @Lob
    @Column(name="comment",columnDefinition = "LONGTEXT",nullable=false)
    public String comment;
    @Column(name="rating",nullable=false)
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idResource")
    @JsonManagedReference
    private Resource resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idUser")
    @JsonManagedReference
    private User user;

}

package com.example.biblioteca.model.entity;

import com.example.biblioteca.pojo.ResourceType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable=false,length=45)
    private String title;

    @Column(name="publishedYear",nullable=false)
    private Integer publishedYear;

    @Column(name="rating",nullable=true)
    private Double rating;

    @Column(name="totalReviews",nullable = true)
    private Integer totalReviews;

    @OneToMany(mappedBy ="resource",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JsonBackReference
    @ToString.Exclude
    private List<Copy> copies;

    @OneToMany(mappedBy = "resource",cascade = jakarta.persistence.CascadeType.MERGE,fetch = FetchType.EAGER)
    @JsonBackReference
    @ToString.Exclude
    private List<Review> reviews;

    @Transient
    private ResourceType type;


}

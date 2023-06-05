package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"titulo","idEditorial"})})
//@PrimaryKeyJoinColumn(name = "IdRecurso")

/*Necesitas agregar @JsonManagedReferences y @JsonBackReferences, se utilizan para mostrar objetos con relación padre-hijo.
@JsonManagedReferences se usa para referirse al objeto padre y @JsonBackReferences se usa para marcar objetos hijos.
Esas anotaciones seran suficiente para resolver el estado de recursividad que esta ocurriendo en tus entidades.
 */

@Data
@Entity

public class Book extends Resource {

    @Column(name="isbn",nullable=false,length=14)
    public String isbn;
    @Lob
    @Column(name="synopsis",columnDefinition = "LONGTEXT",nullable=true)
    public String synopsis;
    @Column(name="pages",nullable=false)
    public Integer pages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idPublisher")
    @JsonManagedReference
    @ToString.Exclude
    private BookPublisher publisher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idGenre")
    @JsonManagedReference
    @ToString.Exclude
    private LiteraryGenre genre;


    @Cascade({org.hibernate.annotations.CascadeType.MERGE})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Book_Writer",
            joinColumns = @JoinColumn(name = "idBook"),
            inverseJoinColumns = @JoinColumn(name = "idWriter")
    )
    @JsonBackReference
    @ToString.Exclude
    private List<Writer> writers;


    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Publisher_Book",
            joinColumns = @JoinColumn(name = "idBook"),
            inverseJoinColumns = @JoinColumn(name = "idPublisher")
    )
    @JsonBackReference
    @ToString.Exclude
    private List<BookPublisher> publishers;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="idIdioma")
    @JsonManagedReference
    @ToString.Exclude
    private Language language;


}




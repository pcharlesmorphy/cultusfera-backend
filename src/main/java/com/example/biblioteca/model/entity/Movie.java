package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
@Entity
public class Movie extends Resource {

    @Column(name="duration",nullable=false)
    public Integer duration;

    @Lob
    @Column(name="synopsis",columnDefinition = "LONGTEXT",nullable=true)
    public String synopsis;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idGenre")
    @JsonManagedReference
    @ToString.Exclude
    private MovieGenre genre;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idDirector")
    @JsonManagedReference
    @ToString.Exclude
    private Director director;


    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Movie_Actor",
            joinColumns = @JoinColumn(name = "idMovie"),
            inverseJoinColumns = @JoinColumn(name = "idActor")
    )
    @JsonBackReference
    @ToString.Exclude
    private List<Actor> actors;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idLanguage")
    @JsonManagedReference
    @ToString.Exclude
    private Language language;

}

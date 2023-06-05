package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="role",discriminatorType = DiscriminatorType.INTEGER)
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name","surnames"})})
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable=false,length=45)
    private String name;

    @Column(name="surnames",nullable=true,length=45)
    private String surnames;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role",insertable = false,updatable = false)
    @JsonManagedReference
    private Role role;

    @OneToMany(mappedBy = "director",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Movie> directorMovies;

    @OneToMany(mappedBy = "musician",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Album> albums;

    @ManyToMany(mappedBy = "actors",cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Movie> movies;

    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @ManyToMany(mappedBy = "writers",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Book> books;


}

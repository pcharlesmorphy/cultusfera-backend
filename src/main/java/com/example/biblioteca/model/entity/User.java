package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable=false,length=25)
    private String name;
    @Column(name="surnames",nullable=false,length=45)
    private String surnames;
    @Column(name="email",nullable=false,length=30)
    private String email;
    @Column(name="nif",nullable=false,length=12)
    private String nif;
    @Column(name="address",nullable=false,length=45)
    private String address;
    @Column(name="phone",nullable=false,length=12)
    private String phone;
    @Column(name="username",nullable=false,length=25)
    private String username;
    @Column(name="password",nullable=false,length=150)
    private String password;
    @Column(name="registrationDate",nullable=false)
    private LocalDate registrationDate;
    @Column(name="suspended",nullable = false)
    private boolean suspended;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="idUserRole")
    @JsonManagedReference
    @ToString.Exclude
    private UserRole role;

    @OneToMany(mappedBy = "user",cascade = jakarta.persistence.CascadeType.MERGE)
    @JsonBackReference
    @ToString.Exclude
    private List<Review> reviews;

    @JsonBackReference
    @OneToMany(mappedBy = "user",cascade = jakarta.persistence.CascadeType.MERGE)
    @ToString.Exclude
    private List<Transaction> transactions;

    @JsonBackReference
    @OneToMany(mappedBy = "user",cascade = jakarta.persistence.CascadeType.MERGE)
    @ToString.Exclude
    private List<Penalty> penalties;

}

package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data

public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="registrationDate",nullable=false)
    @ToString.Exclude
    private LocalDate RegistrationDate;
    @ToString.Exclude
    @Column(name="dismissalDate",nullable = true)
    private LocalDate DismissalDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="idStatus")
    @JsonManagedReference
    @ToString.Exclude
    private StatusCopy status;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="idLocation")
    @JsonManagedReference
    @ToString.Exclude
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @JoinColumn(name="idResource")
    @JsonManagedReference
    @JsonIgnore
    @ToString.Exclude
    private Resource resource;

    @Transient
    private TransactionStatus transactionStatus;

    @JsonBackReference
    @OneToMany(mappedBy = "copy",cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Transaction> transactions;
}

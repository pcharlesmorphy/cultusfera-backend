package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.time.LocalDate;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",discriminatorType = DiscriminatorType.INTEGER)
@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="startDate",nullable=false)
    private LocalDate startDate;

    @Column(name="endDate",nullable=true)
    private LocalDate endDate;

    @OneToOne(mappedBy = "loan")
    @JsonManagedReference
    private Penalty penalty;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idUser")
    @JsonManagedReference
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idStatus")
    @JsonManagedReference
    @ToString.Exclude
    private TransactionStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idCopy")
    @JsonManagedReference
    @ToString.Exclude
    private Copy copy;
}

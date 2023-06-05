package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class PenaltyReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name="reason",nullable=false,length = 40)
    private String reason;

    @OneToMany(mappedBy = "reason",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Penalty> penalties;

}

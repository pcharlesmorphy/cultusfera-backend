package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@PrimaryKeyJoinColumn(name = "IdResource")
@Entity
public class Album extends Resource {
    @Id
    Long id;
    @Column(name="duration",nullable=false)
    public Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="idGenre")
    @JsonManagedReference
    @ToString.Exclude
    private MusicGenre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="idRecordCompany")
    @JsonManagedReference
    @ToString.Exclude
    private RecordCompany recordCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinColumn(name="idMusician")
    @JsonManagedReference
    @ToString.Exclude
    private Musician musician;
}

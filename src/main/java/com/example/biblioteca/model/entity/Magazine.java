package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

@Data
@Entity
public class Magazine extends Resource {

    @Column(name="number",nullable=false)
    private Integer number;

    @Column(name="pages",nullable=false)
    private Integer pages;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idPublisher")
    @JsonManagedReference
    @ToString.Exclude
    private MagazinePublisher publisher;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idMonth")
    @JsonManagedReference
    @ToString.Exclude
    private Month month;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idSubject")
    @JsonManagedReference
    @ToString.Exclude
    private MagazineSubject subject;

    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idLanguage")
    @JsonManagedReference
    @ToString.Exclude
    private Language language;
}

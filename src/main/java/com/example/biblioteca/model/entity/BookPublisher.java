package com.example.biblioteca.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
@Entity
@Schema(description = "Editorial del Libro")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookPublisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY,description = "Identificador",example = "1")
    private Long id;
    @Schema(description = "Nombre de la editorial",example = "Planeta")

    @Column(name="name",unique = true,nullable=false,length=25)
    private String name;

    @Schema(hidden = true)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.ALL})
    @ManyToMany(mappedBy = "publishers",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Book> books;

}

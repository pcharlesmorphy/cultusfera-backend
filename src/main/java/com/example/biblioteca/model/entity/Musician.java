package com.example.biblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("3")
public class Musician extends Author {

}

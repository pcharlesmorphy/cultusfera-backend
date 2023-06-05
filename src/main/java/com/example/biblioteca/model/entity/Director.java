package com.example.biblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("4")
public class Director extends Author {

}

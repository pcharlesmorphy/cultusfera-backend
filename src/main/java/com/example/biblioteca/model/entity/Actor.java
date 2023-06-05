package com.example.biblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("2")
public class Actor extends Author {

}

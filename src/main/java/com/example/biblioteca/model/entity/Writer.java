package com.example.biblioteca.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

@DiscriminatorValue("1")
public class Writer extends Author {

}

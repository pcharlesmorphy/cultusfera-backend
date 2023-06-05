package com.example.biblioteca.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@DiscriminatorValue("1")
public class Booking extends Transaction{

}

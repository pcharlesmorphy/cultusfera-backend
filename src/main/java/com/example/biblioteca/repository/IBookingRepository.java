package com.example.biblioteca.repository;

import com.example.biblioteca.model.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingRepository extends JpaRepository<Booking, Long> {
}

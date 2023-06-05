package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.BookingDTO;
import com.example.biblioteca.model.entity.Booking;

import java.util.List;

public interface IBookingMapper {
    Booking dtoToEntity (BookingDTO bookingDTO);
    BookingDTO entityToDto (Booking booking);
    List<BookingDTO> entityToDtoList (List<Booking> bookings);
    List<Booking> dtoListToEntity (List<BookingDTO> bookingsDTO);
}

package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.BookingDTO;
import com.example.biblioteca.model.entity.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookingMapperImpl implements IBookingMapper{

    @Autowired
    UserMapperImpl userMapper;
    @Autowired
    CopyMapperImpl copyMapper;

    @Autowired
    TransactionStatusMapperImpl transactionStatusMapper;

    @Override
    public Booking dtoToEntity(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setId(bookingDTO.getId());
        booking.setStartDate(bookingDTO.getStartDate());
        booking.setEndDate(bookingDTO.getEndDate());
        //booking.setUser(userMapper.dtoToEntity(bookingDTO.getUser()));
        //booking.setCopy(copyMapper.dtoToEntity(bookingDTO.getCopy()));
        if (bookingDTO.getStatus()!=null) {
            booking.setStatus(transactionStatusMapper.dtoToEntity(bookingDTO.getStatus()));
        }
        return booking;

    }

    @Override
    public BookingDTO entityToDto(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setStartDate(booking.getStartDate());
        bookingDTO.setEndDate(booking.getEndDate());
        //bookingDTO.setUser(userMapper.entityToDto(booking.getUser()));
        //bookingDTO.setCopy(copyMapper.entityToDto(booking.getCopy()));
        if (booking.getStatus()!=null) {
            bookingDTO.setStatus(transactionStatusMapper.entityToDto(booking.getStatus()));
        }
        return bookingDTO;
    }

    @Override
    public List<BookingDTO> entityToDtoList(List<Booking> bookings) {
        List<BookingDTO> bookingsDTO = new ArrayList<BookingDTO>();
        BookingDTO bookingDTO = new BookingDTO();
        for (Booking b:bookings){
            bookingDTO = entityToDto(b);
            bookingsDTO.add(bookingDTO);
        }
        return bookingsDTO;
    }

    @Override
    public List<Booking> dtoListToEntity(List<BookingDTO> bookingsDTO) {
        List<Booking> bookings = new ArrayList<Booking>();
        Booking booking = new Booking();
        for (BookingDTO b:bookingsDTO){
            booking = dtoToEntity(b);
            bookings.add(booking);
        }
        return bookings;
    }
}

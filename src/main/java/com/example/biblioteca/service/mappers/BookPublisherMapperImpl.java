package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.BookPublisherDTO;
import com.example.biblioteca.model.entity.BookPublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookPublisherMapperImpl implements IBookPublisherMapper {

    @Override
    public BookPublisher dtoToEntity(BookPublisherDTO publisherDTO) {
        BookPublisher publisher = new BookPublisher();
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        return publisher;
    }

    @Override
    public BookPublisherDTO entityToDto(BookPublisher publisher) {
        BookPublisherDTO publisherDTO = new BookPublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        return publisherDTO;
    }

    @Override
    public List<BookPublisherDTO> entityToDtoList(List<BookPublisher> publishers) {
        List<BookPublisherDTO> publishersDTO = new ArrayList<BookPublisherDTO>();
        BookPublisherDTO publisherDTO = new BookPublisherDTO();

        for (BookPublisher bp:publishers){
            publisherDTO=entityToDto(bp);
            publishersDTO.add(publisherDTO);
        }
        return publishersDTO;
    }
}

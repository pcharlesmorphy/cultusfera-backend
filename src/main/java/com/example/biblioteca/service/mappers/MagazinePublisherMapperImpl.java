package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.BookPublisherDTO;
import com.example.biblioteca.model.dto.MagazineDTO;
import com.example.biblioteca.model.dto.MagazinePublisherDTO;
import com.example.biblioteca.model.entity.BookPublisher;
import com.example.biblioteca.model.entity.MagazinePublisher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MagazinePublisherMapperImpl implements IMagazinePublisherMapper{
    @Override
    public MagazinePublisher dtoToEntity(MagazinePublisherDTO publisherDTO) {
        MagazinePublisher publisher = new MagazinePublisher();
        publisher.setId(publisherDTO.getId());
        publisher.setName(publisherDTO.getName());
        return publisher;
    }

    @Override
    public MagazinePublisherDTO entityToDto(MagazinePublisher publisher) {
        MagazinePublisherDTO publisherDTO = new MagazinePublisherDTO();
        publisherDTO.setId(publisher.getId());
        publisherDTO.setName(publisher.getName());
        return publisherDTO;
    }

    @Override
    public List<MagazinePublisherDTO> entityToDtoList(List<MagazinePublisher> publishers) {
        List<MagazinePublisherDTO> publishersDTO = new ArrayList<MagazinePublisherDTO>();
        MagazinePublisherDTO publisherDTO = new MagazinePublisherDTO();

        for (MagazinePublisher mp:publishers){
            publisherDTO=entityToDto(mp);
            publishersDTO.add(publisherDTO);
        }
        return publishersDTO;
    }
}

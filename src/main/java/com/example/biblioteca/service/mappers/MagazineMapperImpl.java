package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.AlbumDTO;
import com.example.biblioteca.model.dto.MagazineDTO;
import com.example.biblioteca.model.entity.Album;
import com.example.biblioteca.model.entity.Magazine;
import com.example.biblioteca.model.entity.MagazineSubject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MagazineMapperImpl implements IMagazineMapper{

    @Autowired
    MonthMapperImpl monthMapper;
    @Autowired
    MagazineSubjectMappeImpl magazineSubjectMapper;
    @Autowired
    MagazinePublisherMapperImpl magazinePublisherMapper;
    @Autowired
    LanguageMapperImpl languageMapper;

    @Autowired
    CopyMapperImpl copyMapper;

    @Autowired
    ReviewMapperImpl reviewMapper;

    @Override
    public Magazine dtoToEntity(MagazineDTO magazineDTO) {
        Magazine magazine = new Magazine();
        magazine.setId(magazineDTO.getId());
        magazine.setTitle(magazineDTO.getTitle());
        magazine.setPublishedYear(magazineDTO.getPublishedYear());
        magazine.setPages(magazineDTO.getPages());
        magazine.setNumber(magazineDTO.getNumberMagazine());
        magazine.setMonth(monthMapper.dtoToEntity(magazineDTO.getMonth()));
        magazine.setSubject(magazineSubjectMapper.dtoToEntity(magazineDTO.getSubject()));
        magazine.setPublisher(magazinePublisherMapper.dtoToEntity(magazineDTO.getPublisher()));
        magazine.setLanguage(languageMapper.dtoToEntity(magazineDTO.getLanguage()));
        magazine.setType(magazineDTO.getType());
        if (magazineDTO.getCopies() != null){
            magazine.setCopies(copyMapper.dtoListToEntity(magazineDTO.getCopies()));
        }
        if (magazineDTO.getReviews() != null){
            magazine.setReviews(reviewMapper.dtoListToEntity(magazineDTO.getReviews()));
        }
        magazine.setRating(magazineDTO.getRating());
        return magazine;
    }

    @Override
    public MagazineDTO entityToDto(Magazine magazine) {
        MagazineDTO magazineDTO = new MagazineDTO();
        magazineDTO.setId(magazine.getId());
        magazineDTO.setTitle(magazine.getTitle());
        magazineDTO.setPublishedYear(magazine.getPublishedYear());
        magazineDTO.setPages(magazine.getPages());
        magazineDTO.setNumberMagazine(magazine.getNumber());
        magazineDTO.setMonth(monthMapper.entityToDto(magazine.getMonth()));
        magazineDTO.setSubject(magazineSubjectMapper.entityToDto(magazine.getSubject()));
        magazineDTO.setPublisher(magazinePublisherMapper.entityToDto(magazine.getPublisher()));
        magazineDTO.setLanguage(languageMapper.entityToDto(magazine.getLanguage()));
        magazineDTO.setType(magazine.getType());
        if (magazine.getCopies() != null){
            magazineDTO.setCopies(copyMapper.entityToDtoList(magazine.getCopies()));
        }
        if (magazine.getReviews() != null){
            magazineDTO.setReviews(reviewMapper.entityToDtoList(magazine.getReviews()));
        }
        magazineDTO.setRating(magazine.getRating());
        return magazineDTO;
    }

    @Override
    public List<MagazineDTO> entityToDtoList(List<Magazine> magazines) {
        List<MagazineDTO> magazinesDTO = new ArrayList<MagazineDTO>();
        for (Magazine m: magazines){
            magazinesDTO.add(entityToDto(m));
        }

        return magazinesDTO;
    }
}

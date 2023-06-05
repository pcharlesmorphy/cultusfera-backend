package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MagazineSubjectDTO;
import com.example.biblioteca.model.entity.MagazineSubject;

import java.util.List;

public interface IMagazineSubjectMapper {
    MagazineSubject dtoToEntity (MagazineSubjectDTO subjectDTO);
    MagazineSubjectDTO entityToDto (MagazineSubject subject);
    List<MagazineSubjectDTO> entityToDtoList (List<MagazineSubject> subjects);
}

package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MagazinePublisherDTO;
import com.example.biblioteca.model.dto.MagazineSubjectDTO;
import com.example.biblioteca.model.entity.MagazinePublisher;
import com.example.biblioteca.model.entity.MagazineSubject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MagazineSubjectMappeImpl implements IMagazineSubjectMapper{

    @Override
    public MagazineSubject dtoToEntity(MagazineSubjectDTO subjectDTO) {
        MagazineSubject subject = new MagazineSubject();
        subject.setId(subjectDTO.getId());
        subject.setName(subjectDTO.getName());
        return subject;
    }

    @Override
    public MagazineSubjectDTO entityToDto(MagazineSubject subject) {
        MagazineSubjectDTO subjectDTO = new MagazineSubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        return subjectDTO;
    }

    @Override
    public List<MagazineSubjectDTO> entityToDtoList(List<MagazineSubject> subjects) {
        return null;
    }
}

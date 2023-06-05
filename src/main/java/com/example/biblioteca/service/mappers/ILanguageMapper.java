package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.LanguageDTO;
import com.example.biblioteca.model.entity.Language;

import java.util.List;

public interface ILanguageMapper {
    Language dtoToEntity (LanguageDTO languageDTO);
    LanguageDTO entityToDto (Language language);
    List<LanguageDTO> entityToDtoList (List<Language> language);
}

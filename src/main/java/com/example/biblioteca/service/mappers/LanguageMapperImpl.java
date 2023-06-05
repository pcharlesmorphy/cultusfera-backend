package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.LanguageDTO;
import com.example.biblioteca.model.entity.Language;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageMapperImpl implements ILanguageMapper {
    @Override
    public Language dtoToEntity(LanguageDTO languageDTO) {
        Language language = new Language();
        language.setId(languageDTO.getId());
        language.setName(languageDTO.getName());
        return language;
    }

    @Override
    public LanguageDTO entityToDto(Language language) {
        LanguageDTO languageDTO = new LanguageDTO();
        languageDTO.setId(language.getId());
        languageDTO.setName(language.getName());
        return languageDTO;
    }

    @Override
    public List<LanguageDTO> entityToDtoList(List<Language> language) {
        return null;
    }
}

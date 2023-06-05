package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.WriterDTO;
import com.example.biblioteca.model.entity.Writer;

import java.util.List;

public interface IWriterMapper {
    Writer dtoToEntity (WriterDTO writerDTO);
    WriterDTO entityToDto (Writer writer);
    List<WriterDTO> entityToDtoList (List<Writer> writer);
    List<Writer> dtoListToEntity (List<WriterDTO> writersDTO);
}

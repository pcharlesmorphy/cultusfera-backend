package com.example.biblioteca.service.mappers;
import com.example.biblioteca.model.dto.StatusCopyDTO;
import com.example.biblioteca.model.entity.StatusCopy;

import java.util.List;

public interface IStatusCopyMapper {
    StatusCopy dtoToEntity (StatusCopyDTO statusDTO);
    StatusCopyDTO entityToDto (StatusCopy status);
    List<StatusCopyDTO> entityToDtoList (List<StatusCopy> status);
}

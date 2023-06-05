package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MonthDTO;
import com.example.biblioteca.model.entity.Month;

import java.util.List;

public interface IMonthMapper {
    Month dtoToEntity (MonthDTO monthDTO);
    MonthDTO entityToDto (Month month);
    List<MonthDTO> entityToDtoList (List<Month> months);
}

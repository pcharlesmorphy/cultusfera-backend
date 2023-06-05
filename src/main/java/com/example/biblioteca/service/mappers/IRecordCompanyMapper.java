package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.RecordCompanyDTO;
import com.example.biblioteca.model.entity.RecordCompany;

import java.util.List;

public interface IRecordCompanyMapper {
    RecordCompany dtoToEntity (RecordCompanyDTO recordCompanyDTO);
    RecordCompanyDTO entityToDto (RecordCompany recordCompany);
    List<RecordCompanyDTO> entityToDtoList (List<RecordCompany> recordCompanies);
}

package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.RecordCompanyDTO;
import com.example.biblioteca.model.entity.RecordCompany;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecordCompanyMapperImpl implements IRecordCompanyMapper{
    @Override
    public RecordCompany dtoToEntity(RecordCompanyDTO recordCompanyDTO) {
        RecordCompany recordCompany = new RecordCompany();
        recordCompany.setId(recordCompanyDTO.getId());
        recordCompany.setName(recordCompanyDTO.getName());
        return recordCompany;
    }

    @Override
    public RecordCompanyDTO entityToDto(RecordCompany recordCompany) {
        RecordCompanyDTO recordCompanyDTO = new RecordCompanyDTO();
        recordCompanyDTO.setId(recordCompany.getId());
        recordCompanyDTO.setName(recordCompany.getName());
        return recordCompanyDTO;
    }

    @Override
    public List<RecordCompanyDTO> entityToDtoList(List<RecordCompany> recordCompanies) {
        List<RecordCompanyDTO> recordCompaniesDTO = new ArrayList<RecordCompanyDTO>();
        RecordCompanyDTO recordCompanyDTO = new RecordCompanyDTO();

        for (RecordCompany rc:recordCompanies){
            recordCompanyDTO=entityToDto(rc);
            recordCompaniesDTO.add(recordCompanyDTO);
        }
        return recordCompaniesDTO;
    }
}

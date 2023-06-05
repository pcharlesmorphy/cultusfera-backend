package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.CopyResourceDTO;
import com.example.biblioteca.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CopyResourceMapperImpl implements ICopyResourceMapper {

    @Autowired
    TransactionStatusMapperImpl transactionStatusMapper;
    @Autowired
    StatusCopyMapperImpl statusCopyMapper;

    @Autowired
    LocationMapperImpl locationMapper;

    @Override
    public Copy dtoToEntity(CopyResourceDTO copyDTO) {
        Copy copy = new Copy();
        copy.setId(copyDTO.getId());
        copy.setStatus(statusCopyMapper.dtoToEntity(copyDTO.getStatus()));
        copy.setLocation(locationMapper.dtoToEntity(copyDTO.getLocation()));
        copy.setTransactionStatus(transactionStatusMapper.dtoToEntity(copyDTO.getTransactionStatus()));

        return copy;

    }

    @Override
    public CopyResourceDTO entityToDto(Copy copy) {
        CopyResourceDTO copyDTO = new CopyResourceDTO();
        copyDTO.setId(copy.getId());
        copyDTO.setStatus(statusCopyMapper.entityToDto(copy.getStatus()));
        copyDTO.setLocation(locationMapper.entityToDto(copy.getLocation()));
        copyDTO.setTransactionStatus(transactionStatusMapper.entityToDto(copy.getTransactionStatus()));

        return copyDTO;
    }

    @Override
    public List<CopyResourceDTO> entityToDtoList(List<Copy> copies) {
        List<CopyResourceDTO> copiesDTO = new ArrayList<CopyResourceDTO>();
        CopyResourceDTO copyDTO = new CopyResourceDTO();
        for (Copy c : copies) {
            copyDTO = entityToDto(c);
            copiesDTO.add(copyDTO);
        }
        return copiesDTO;
    }

    @Override
    public List<Copy> dtoListToEntity(List<CopyResourceDTO> copiesDTO) {
        List<Copy> copies = new ArrayList<Copy>();
        Copy copy = new Copy();
        for (CopyResourceDTO c : copiesDTO) {
            copy = dtoToEntity(c);
            copies.add(copy);
        }
        return copies;
    }
}
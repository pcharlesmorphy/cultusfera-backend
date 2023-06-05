package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.CopyDTO;
import com.example.biblioteca.model.entity.Copy;
import com.example.biblioteca.model.entity.Resource;
import com.example.biblioteca.service.ResourceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CopyMapperImpl implements ICopyMapper{

    @Autowired
    LocationMapperImpl locationMapper;
    @Autowired
    StatusCopyMapperImpl statusCopyMapper;

    @Autowired
    ResourceMapperImpl resourceMapper;

    @Autowired
    ResourceServiceImpl resourceService;

    @Autowired
    TransactionMapperImpl transactionMapper;

    @Autowired
    TransactionStatusMapperImpl transactionStatusMapper;

    @Override
    public Copy dtoToEntity(CopyDTO copyDTO) {
        Copy copy = new Copy();
        copy.setId(copyDTO.getId());
        copy.setRegistrationDate(copyDTO.getRegistrationDate());
        copy.setDismissalDate(copyDTO.getDismissalDate());
        copy.setLocation(locationMapper.dtoToEntity(copyDTO.getLocation()));
        copy.setStatus(statusCopyMapper.dtoToEntity(copyDTO.getStatus()));
        copy.setResource(resourceMapper.dtoToEntity(copyDTO.getResource()));
        if (copyDTO.getTransactions() != null) {
            copy.setTransactions(transactionMapper.dtoListToEntity(copyDTO.getTransactions()));
        }
        return copy;
    }

    @Override
    public CopyDTO entityToDto(Copy copy) {
        CopyDTO copyDTO = new CopyDTO();
        copyDTO.setId(copy.getId());
        copyDTO.setRegistrationDate(copy.getRegistrationDate());
        copyDTO.setDismissalDate(copy.getDismissalDate());
        copyDTO.setLocation(locationMapper.entityToDto(copy.getLocation()));
        copyDTO.setStatus(statusCopyMapper.entityToDto(copy.getStatus()));
        copyDTO.setResource(resourceMapper.entityToDto(copy.getResource()));
        if (copy.getTransactions() != null) {
            copyDTO.setTransactions(transactionMapper.entityToDtoList(copy.getTransactions()));
        }
        return copyDTO;
    }

    @Override
    public List<CopyDTO> entityToDtoList(List<Copy> copies) {
        List<CopyDTO> copiesDTO = new ArrayList<CopyDTO>();
        CopyDTO copyDTO = new CopyDTO();
        for (Copy c:copies){
            copyDTO = entityToDto(c);
            copiesDTO.add(copyDTO);
        }
        return copiesDTO;
    }

    @Override
    public List<Copy> dtoListToEntity(List<CopyDTO> copiesDTO) {
        List<Copy> copies = new ArrayList<Copy>();
        Copy copy = new Copy();
        for (CopyDTO c:copiesDTO){
            copy = dtoToEntity(c);
            copies.add(copy);
        }
        return copies;
    }

    private Resource getResourceById (Long idResource){
        return resourceService.findResourceById(idResource).get();
    }
}

package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.TransactionStatusDTO;
import com.example.biblioteca.model.entity.Transaction;
import com.example.biblioteca.model.entity.TransactionStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionStatusMapperImpl implements ITransactionStatusMapper{
    @Override
    public TransactionStatus dtoToEntity(TransactionStatusDTO statusDTO) {
        TransactionStatus status = new TransactionStatus();
        status.setId(statusDTO.getId());
        status.setType(statusDTO.getType());
        return status;
    }

    @Override
    public TransactionStatusDTO entityToDto(TransactionStatus status) {
        TransactionStatusDTO statusDTO = new TransactionStatusDTO();
        statusDTO.setId(status.getId());
        statusDTO.setType(status.getType());
        return statusDTO;
    }

    @Override
    public List<TransactionStatusDTO> entityToDtoList(List<TransactionStatus> statusList) {
        List<TransactionStatusDTO> statusDTOList = new ArrayList<TransactionStatusDTO>();
        TransactionStatusDTO statusDTO = new TransactionStatusDTO();
        for (TransactionStatus ts:statusList){
            statusDTO = entityToDto(ts);
            statusDTOList.add(statusDTO);
        }
        return statusDTOList;
    }

    @Override
    public List<TransactionStatus> dtoListToEntity(List<TransactionStatusDTO> statusDTOList) {
        List<TransactionStatus> statusList = new ArrayList<TransactionStatus>();
        TransactionStatus status = new TransactionStatus();
        for (TransactionStatusDTO ts:statusDTOList){
            status = dtoToEntity(ts);
            statusList.add(status);
        }
        return statusList;
    }
}

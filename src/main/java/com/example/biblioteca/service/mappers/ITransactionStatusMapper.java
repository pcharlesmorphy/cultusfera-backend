package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.TransactionStatusDTO;
import com.example.biblioteca.model.entity.TransactionStatus;

import java.util.List;

public interface ITransactionStatusMapper {
    TransactionStatus dtoToEntity (TransactionStatusDTO statusDTO);
    TransactionStatusDTO entityToDto (TransactionStatus status);
    List<TransactionStatusDTO> entityToDtoList (List<TransactionStatus> statusList);
    List<TransactionStatus> dtoListToEntity (List<TransactionStatusDTO> statusDTOList);
}

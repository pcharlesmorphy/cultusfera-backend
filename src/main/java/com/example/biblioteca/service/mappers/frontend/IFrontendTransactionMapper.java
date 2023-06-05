package com.example.biblioteca.service.mappers.frontend;

import com.example.biblioteca.model.dto.frontend.FrontendTransactionDTO;
import com.example.biblioteca.model.entity.Transaction;

import java.util.List;

public interface IFrontendTransactionMapper {

    FrontendTransactionDTO entityToDto (Transaction transaction);
    List<FrontendTransactionDTO> entityToDtoList (List<Transaction> transactions);
}

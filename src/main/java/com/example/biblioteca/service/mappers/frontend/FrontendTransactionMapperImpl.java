package com.example.biblioteca.service.mappers.frontend;

import com.example.biblioteca.model.dto.frontend.FrontendTransactionDTO;
import com.example.biblioteca.model.entity.Resource;
import com.example.biblioteca.model.entity.Transaction;
import com.example.biblioteca.service.ResourceServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class FrontendTransactionMapperImpl implements IFrontendTransactionMapper{

    @Autowired
    ResourceServiceImpl resourceService;

    @Override
    public FrontendTransactionDTO entityToDto(Transaction transaction) {
        FrontendTransactionDTO frontendTransaction = new FrontendTransactionDTO();
        frontendTransaction.setResourceTitle(transaction.getCopy().getResource().getTitle());
        frontendTransaction.setPublishedYear(transaction.getCopy().getResource().getPublishedYear());

        Resource resource = transaction.getCopy().getResource();
        resourceService.classifyResource(resource);

        frontendTransaction.setResourceType(resource.getType().getType());
        frontendTransaction.setResourceLocation(transaction.getCopy().getLocation().getName());
        frontendTransaction.setStartDate(transaction.getStartDate());
        frontendTransaction.setEndDate(transaction.getEndDate());
        frontendTransaction.setUser(transaction.getUser().getName().concat(" ").concat(transaction.getUser().getSurnames()));
        frontendTransaction.setTransactionStatus(transaction.getStatus().getType());

        return frontendTransaction;
    }

    @Override
    public List<FrontendTransactionDTO> entityToDtoList(List<Transaction> transactions) {
        List <FrontendTransactionDTO> frontendTransactions = new ArrayList<FrontendTransactionDTO>();
        FrontendTransactionDTO frontendTransaction = new FrontendTransactionDTO();
        for (Transaction t:transactions){
            frontendTransaction = entityToDto(t);
            frontendTransactions.add(frontendTransaction);
        }
        return frontendTransactions;
    }
}


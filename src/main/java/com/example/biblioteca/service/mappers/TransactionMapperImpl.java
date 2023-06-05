package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.TransactionDTO;
import com.example.biblioteca.model.entity.Transaction;
import com.example.biblioteca.service.CopyServiceImpl;
import com.example.biblioteca.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionMapperImpl implements ITransactionMapper{

    @Autowired
    PenaltyMapperImpl penaltyMapper;
    @Autowired
    TransactionStatusMapperImpl transactionStatusMapper;
    @Autowired
    CopyMapperImpl copyMapper;
    @Autowired
    UserMapperImpl userMapper;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    CopyServiceImpl copyService;



    @Override
    public Transaction dtoToEntity(TransactionDTO transactionDTO) {
        Transaction transaction = new Transaction();
        transaction.setId(transactionDTO.getId());
        transaction.setStartDate(transactionDTO.getStartDate());
        transaction.setEndDate(transactionDTO.getEndDate());
        //transaction.setPenalty(penaltyMapper.dtoToEntity(transactionDTO.getPenalty()));
        transaction.setStatus(transactionStatusMapper.dtoToEntity(transactionDTO.getStatus()));
        transaction.setCopy(copyService.findCopyById(transactionDTO.getId()).get());
        transaction.setUser(userService.findById(transactionDTO.getUserId()).get());


        return transaction;
    }

    @Override
    public TransactionDTO entityToDto(Transaction transaction) {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(transaction.getId());
        transactionDTO.setStartDate(transaction.getStartDate());
        transactionDTO.setEndDate(transaction.getEndDate());
        //transactionDTO.setPenalty(penaltyMapper.entityToDto(transaction.getPenalty()));
        transactionDTO.setStatus(transactionStatusMapper.entityToDto(transaction.getStatus()));
        transactionDTO.setCopyId(transaction.getCopy().getId());
        transactionDTO.setUserId(transaction.getUser().getId());
        return transactionDTO;
    }

    @Override
    public List<TransactionDTO> entityToDtoList(List<Transaction> transactions) {
        List<TransactionDTO> transactionsDTO = new ArrayList<TransactionDTO>();
        TransactionDTO transactionDTO = new TransactionDTO();
        for (Transaction t:transactions){
            transactionDTO = entityToDto(t);
            transactionsDTO.add(transactionDTO);
        }

        return transactionsDTO;
    }

    @Override
    public List<Transaction> dtoListToEntity(List<TransactionDTO> transactionsDTO) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Transaction transaction = new Transaction();
        for (TransactionDTO t:transactionsDTO){
            transaction = dtoToEntity(t);
            transactions.add(transaction);
        }

        return transactions;
    }
}

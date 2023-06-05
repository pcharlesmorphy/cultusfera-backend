package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.repository.ICopyRepository;
import com.example.biblioteca.repository.IStatusCopyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CopyServiceImpl implements ICopyService{

    @Autowired
    ICopyRepository copyRepo;
    @Autowired
    IStatusCopyRepository statusRepo;

    @Autowired
    TransactionServiceImpl transactionService;

    @Transactional(readOnly = true)
    @Override
    public List<Copy> findAll() {
        List<Copy> copies = copyRepo.findAll();
        for (Copy c:copies){
            c.setTransactionStatus(transactionService.findStatusCopy(c.getId()).get());
        }
        return copies;
    }

    @Transactional
    @Override
    public Copy save(Copy copy) {
        return copyRepo.save(copy);
    }

    @Transactional
    @Override
    public Boolean delete(Long id) {

        Copy copy = findCopyById(id).get();
        StatusCopy status = findStatusById(5L).get();

        if (copy.getStatus().getStatus().equals("Baja")) return false;
        copy.setStatus(status);
        return true;
    }

    @Transactional
    @Override
    public Copy update(Copy copy) {
        return copyRepo.save(copy);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Copy> findCopyById(Long id) {
        return Optional.ofNullable(copyRepo.findById(id)).orElse(null);
    }

    @Override
    public Optional<StatusCopy> findStatusById(Long id) {
        return Optional.ofNullable(statusRepo.findById(id)).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Copy> findAllResourceCopies(Resource resource) {
        return copyRepo.findAllByResource(resource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findCopyActiveTransactions (Copy copy){
        List<Transaction> transactions = copy.getTransactions();
        List<Transaction> activeTransactions = new ArrayList<>();
        for (Transaction t:transactions){
            if (t.getStatus().getId() == 1){
                activeTransactions.add(t);
            }
        }
        return activeTransactions;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findCopyHistoricTransactions (Copy copy){
        List<Transaction> transactions = copy.getTransactions();
        List<Transaction> activeTransactions = new ArrayList<>();
        for (Transaction t:transactions){
            if (t.getStatus().getId() == 2 || t.getStatus().getId() == 2){
                activeTransactions.add(t);
            }
        }
        return activeTransactions;
    }


}

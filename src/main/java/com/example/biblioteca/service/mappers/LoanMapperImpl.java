package com.example.biblioteca.service.mappers;


import com.example.biblioteca.model.dto.LoanDTO;

import com.example.biblioteca.model.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoanMapperImpl implements ILoanMapper{
    @Autowired
    UserMapperImpl userMapper;
    @Autowired
    CopyMapperImpl copyMapper;

    @Autowired
    TransactionStatusMapperImpl transactionStatusMapper;

    @Override
    public Loan dtoToEntity(LoanDTO loanDTO) {
        Loan loan = new Loan();
        loan.setId(loanDTO.getId());
        loan.setStartDate(loanDTO.getStartDate());
        loan.setEndDate(loanDTO.getEndDate());
        //loan.setUser(userMapper.dtoToEntity(loanDTO.getUser()));
        //loan.setCopy(copyMapper.dtoToEntity(loanDTO.getCopy()));
        loan.setStatus(transactionStatusMapper.dtoToEntity(loanDTO.getStatus()));
        return loan;
    }

    @Override
    public LoanDTO entityToDto(Loan loan) {
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(loan.getId());
        loanDTO.setStartDate(loan.getStartDate());
        loanDTO.setEndDate(loan.getEndDate());
        //loanDTO.setUser(userMapper.entityToDto(loan.getUser()));
        //loanDTO.setCopy(copyMapper.entityToDto(loan.getCopy()));
        loanDTO.setStatus(transactionStatusMapper.entityToDto(loan.getStatus()));
        return loanDTO;
    }

    @Override
    public List<LoanDTO> entityToDtoList(List<Loan> loans) {
        List<LoanDTO> loansDTO = new ArrayList<LoanDTO>();
        LoanDTO loanDTO = new LoanDTO();
        for (Loan b:loans){
            loanDTO = entityToDto(b);
            loansDTO.add(loanDTO);
        }
        return loansDTO;
    }

    @Override
    public List<Loan> dtoListToEntity(List<LoanDTO> loansDTO) {
        List<Loan> loans = new ArrayList<Loan>();
        Loan loan = new Loan();
        for (LoanDTO b:loansDTO){
            loan = dtoToEntity(b);
            loans.add(loan);
        }
        return loans;
    }

}

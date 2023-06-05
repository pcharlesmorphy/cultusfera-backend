package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.LoanDTO;
import com.example.biblioteca.model.entity.Loan;

import java.util.List;

public interface ILoanMapper {
    Loan dtoToEntity (LoanDTO loanDTO);
    LoanDTO entityToDto (Loan loan);
    List<LoanDTO> entityToDtoList (List<Loan> loans);
    List<Loan> dtoListToEntity (List<LoanDTO> loansDTO);
}

package com.example.biblioteca.model.dto.backend;

import com.example.biblioteca.model.dto.TransactionStatusDTO;
import com.example.biblioteca.model.entity.TransactionStatus;
import lombok.Data;

@Data
public class ReturnLoanDTO {
    private Long id;
    private TransactionStatusDTO status;
}

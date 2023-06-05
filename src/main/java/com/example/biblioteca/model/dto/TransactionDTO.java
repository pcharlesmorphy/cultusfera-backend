package com.example.biblioteca.model.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {

    private Long Id;
    private LocalDate startDate;
    //private PenaltyDTO penalty;
    private Long userId;
    private TransactionStatusDTO status;
    private Long copyId;
    private LocalDate endDate;

}

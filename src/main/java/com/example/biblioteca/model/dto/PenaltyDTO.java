package com.example.biblioteca.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PenaltyDTO {

    private Long Id;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean status;
    private PenaltyReasonDTO reason;
    private String comments;
    private UserDTO user;
    private LoanDTO loan;
}

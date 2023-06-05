package com.example.biblioteca.model.dto.frontend;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FrontendTransactionDTO {

        private String resourceTitle;
        private Integer publishedYear;
        private String resourceType;
        private String resourceLocation;
        private LocalDate startDate;
        private LocalDate endDate;
        private String user;
        private String transactionStatus;

}


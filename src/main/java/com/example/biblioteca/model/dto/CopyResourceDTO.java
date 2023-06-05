package com.example.biblioteca.model.dto;

import lombok.Data;

import java.util.List;


@Data
public class CopyResourceDTO {

    private Long Id;
    private StatusCopyDTO status;
    private LocationDTO location;
    private TransactionStatusDTO transactionStatus;

}

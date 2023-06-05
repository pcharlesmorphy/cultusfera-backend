package com.example.biblioteca.model.dto;


import com.example.biblioteca.model.entity.Resource;
import lombok.Data;
import org.springframework.transaction.TransactionStatus;


import java.time.LocalDate;
import java.util.List;

@Data
public class CopyDTO {
    Long Id;
    LocalDate registrationDate;
    LocalDate dismissalDate;
    private StatusCopyDTO status;
    private LocationDTO location;
    private ResourceDTO Resource;
    List<TransactionDTO> transactions;

}

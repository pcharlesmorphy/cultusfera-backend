package com.example.biblioteca.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatusCopyDTO {

    Long Id;
    String status;
    private List<CopyDTO> copies;

}

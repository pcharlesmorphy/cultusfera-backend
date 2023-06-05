package com.example.biblioteca.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDTO {

    private Long id;
    private LocalDate date;
    private String title;
    private  String comment;
    private Integer rating;
    private Long resourceId;
    private Long userId;
    private String username;
    private String resourceTitle;

}

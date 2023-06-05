package com.example.biblioteca.model.dto;

import lombok.Data;

@Data
public class MagazineDTO extends ResourceDTO{

    private Long id;
    private String title;
    private Integer publishedYear;
    private Integer pages;
    private MonthDTO month;
    private Integer numberMagazine;
    private MagazinePublisherDTO publisher;
    private MagazineSubjectDTO subject;
    private LanguageDTO language;

}

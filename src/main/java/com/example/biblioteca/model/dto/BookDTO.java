package com.example.biblioteca.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO extends ResourceDTO{

    private Long id;
    private String title;
    private Integer publishedYear;
    private String isbn;
    private String synopsis;
    private Integer pages;
    private BookPublisherDTO publisher;
    private LiteraryGenreDTO genre;
    private List<WriterDTO> writers;
    private LanguageDTO language;

}

package com.example.biblioteca.model.dto;


import lombok.Data;

import java.util.List;

@Data
public class AlbumDTO extends ResourceDTO{
    private Long id;
    private String title;
    private MusicianDTO musician;
    private Integer publishedYear;
    private Integer duration;
    private MusicGenreDTO genre;
    private RecordCompanyDTO recordCompany;
}

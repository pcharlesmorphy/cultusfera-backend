package com.example.biblioteca.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class MovieDTO extends ResourceDTO {

    private Long id;
    private String title;
    private String synopsis;
    private Integer duration;
    private MovieGenreDTO genre;
    private DirectorDTO director;
    private List<ActorDTO> actors;
    private LanguageDTO language;
    private Integer publishedYear;

}

package com.example.biblioteca.model.dto;


import com.example.biblioteca.pojo.ResourceType;
import lombok.Data;

import java.util.List;


@Data
public class ResourceDTO {

    private Long id;
    private String title;
    private Integer publishedYear;
    private ResourceType type;
    private List<CopyDTO> copies;
    private List<ReviewDTO> reviews;
    private Double rating;

}

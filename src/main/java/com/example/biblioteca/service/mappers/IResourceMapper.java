package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.ResourceDTO;
import com.example.biblioteca.model.entity.Resource;


import java.util.List;

public interface IResourceMapper {

    Resource dtoToEntity (ResourceDTO resourceDTO);
    ResourceDTO entityToDto (Resource resource);
    List<ResourceDTO> entityToDtoList (List<Resource> resources);
    
}

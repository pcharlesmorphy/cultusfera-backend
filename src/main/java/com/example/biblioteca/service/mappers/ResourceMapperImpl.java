package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.ResourceDTO;
import com.example.biblioteca.model.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResourceMapperImpl implements IResourceMapper{

    @Autowired
    CopyMapperImpl copyMapper;

    @Override
    public Resource dtoToEntity(ResourceDTO resourceDTO) {
        Resource resource = new Resource();
        resource.setId(resourceDTO.getId());
        resource.setTitle(resourceDTO.getTitle());
        resource.setPublishedYear(resourceDTO.getPublishedYear());
        resource.setType(resourceDTO.getType());
        //resource.setCopies(copyMapper.dtoListToEntity(resourceDTO.getCopies()));
        resource.setRating(resourceDTO.getRating());
        return resource;
    }

    @Override
    public ResourceDTO entityToDto(Resource resource) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(resource.getId());
        resourceDTO.setTitle(resource.getTitle());
        resourceDTO.setPublishedYear(resource.getPublishedYear());
        resourceDTO.setType(resource.getType());
        //resourceDTO.setCopies(copyMapper.entityToDtoList(resource.getCopies()));
        resourceDTO.setRating(resource.getRating());
        return resourceDTO;
    }

    @Override
    public List<ResourceDTO> entityToDtoList(List<Resource> resources) {
        List<ResourceDTO> resourcesDTO = new ArrayList<ResourceDTO>();
        for (Resource r: resources){
            resourcesDTO.add(entityToDto(r));
        }

        return resourcesDTO;
    }
}

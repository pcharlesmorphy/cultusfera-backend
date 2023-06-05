package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.pojo.ResourceType;
import com.example.biblioteca.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImpl implements IResourceService {
    @Autowired
    IResourceRepository resourceRepo;
    @Autowired
    IBookRepository bookRepo;
    @Autowired
    IAlbumRepository albumRepo;
    @Autowired
    IMovieRepository movieRepo;
    @Autowired
    IMagazineRepository magazineRepo;

    @Autowired
    ICopyRepository copyRepo;

    @Autowired
    CopyServiceImpl copyService;

    @Override
    @Transactional
    public Resource save(Resource resource) {
        return resourceRepo.save(resource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Resource> findAll() {

        List<Resource> resources = new ArrayList<Resource>();
        resources.addAll(bookRepo.findAll());
        resources.addAll(albumRepo.findAll());
        resources.addAll(movieRepo.findAll());
        resources.addAll(magazineRepo.findAll());
        return classifyResources(resources);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Resource> findAllByTitleContainsIgnoreCase(String title) {

        List<Resource> resources = new ArrayList<Resource>();
        resources.addAll(bookRepo.findByTitleContainsIgnoreCase(title));
        resources.addAll(albumRepo.findByTitleContainsIgnoreCase(title));
        resources.addAll(movieRepo.findByTitleContainsIgnoreCase(title));
        resources.addAll(magazineRepo.findByTitleContainsIgnoreCase(title));
        return classifyResources(resources);

    }
    @Transactional(readOnly = true)
    @Override
    public Optional<Resource> findResourceById(Long id) {
        return Optional.ofNullable(resourceRepo.findById(id)).orElse(null);
    }


    public List<Resource> classifyResources (List<Resource> resources) {
        for (Resource r : resources) {
            if (r instanceof Book) {
                r.setType(ResourceType.LIBRO);
            } else if (r instanceof Movie) {
                r.setType(ResourceType.PELICULA);
            } else if (r instanceof Album) {
                r.setType(ResourceType.ALBUM);
            } else if (r instanceof Magazine) {
                r.setType(ResourceType.REVISTA);
            }
        }
        return resources;
    }

    public Resource classifyResource (Resource resource) {

            if (resource instanceof Book) {
                resource.setType(ResourceType.LIBRO);
            } else if (resource instanceof Movie) {
                resource.setType(ResourceType.PELICULA);
            } else if (resource instanceof Album) {
                resource.setType(ResourceType.ALBUM);
            } else if (resource instanceof Magazine) {
                resource.setType(ResourceType.REVISTA);
            }

        return resource;
    }

}

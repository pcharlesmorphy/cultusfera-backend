package com.example.biblioteca.service.mappers;


import com.example.biblioteca.model.dto.LocationDTO;
import com.example.biblioteca.model.entity.Location;

import java.util.List;

public interface ILocationMapper {
    Location dtoToEntity (LocationDTO locationDTO);
    LocationDTO entityToDto (Location location);
    List<LocationDTO> entityToDtoList (List<Location> locations);
}

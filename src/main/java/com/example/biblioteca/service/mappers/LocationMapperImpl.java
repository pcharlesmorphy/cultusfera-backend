package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.LocationDTO;
import com.example.biblioteca.model.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LocationMapperImpl implements ILocationMapper{


    @Override
    public Location dtoToEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setId(locationDTO.getId());
        location.setName(locationDTO.getName());
        location.setAddress(locationDTO.getAddress());
        location.setPhone(locationDTO.getPhone());

        return location;
    }

    @Override
    public LocationDTO entityToDto(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(location.getId());
        locationDTO.setName(location.getName());
        locationDTO.setAddress(location.getAddress());
        locationDTO.setPhone(location.getPhone());

        return locationDTO;
    }

    @Override
    public List<LocationDTO> entityToDtoList(List<Location> locations) {
        List<LocationDTO> locationsDTO = new ArrayList<LocationDTO>();
        LocationDTO locationDTO = new LocationDTO();
        for (Location l:locations){
            locationDTO = entityToDto(l);
            locationsDTO.add(locationDTO);
        }
        return locationsDTO;
    }
}

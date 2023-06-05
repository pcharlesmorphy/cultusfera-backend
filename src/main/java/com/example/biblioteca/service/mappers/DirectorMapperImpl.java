package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.DirectorDTO;
import com.example.biblioteca.model.dto.DirectorDTO;
import com.example.biblioteca.model.entity.Director;
import com.example.biblioteca.model.entity.Director;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DirectorMapperImpl implements IDirectorMapper{
    @Override
    public Director dtoToEntity(DirectorDTO directorDTO) {
        Director Director = new Director();
        Director.setId(directorDTO.getId());
        Director.setName(directorDTO.getName());
        Director.setSurnames(directorDTO.getSurnames());
        return Director;
    }

    @Override
    public DirectorDTO entityToDto(Director director) {
        DirectorDTO Directordto = new DirectorDTO();
        Directordto.setId(director.getId());
        Directordto.setName(director.getName());
        Directordto.setSurnames(director.getSurnames());
        return Directordto;
    }

    @Override
    public List<DirectorDTO> entityToDtoList(List<Director> directors) {
        List<DirectorDTO> directorsdto = new ArrayList<DirectorDTO>();
        DirectorDTO Directordto = new DirectorDTO();

        for (Director d:directors){
            Directordto = entityToDto(d);
            directorsdto.add(Directordto);
        }
        return directorsdto;
    }

    @Override
    public List<Director> dtoListToEntity(List<DirectorDTO> directorsDTO) {
        List<Director> directors = new ArrayList<Director>();
        Director Director = new Director();
        for (DirectorDTO d:directorsDTO){
            Director = dtoToEntity(d);
            directors.add(Director);
        }
        return directors;
    }
}

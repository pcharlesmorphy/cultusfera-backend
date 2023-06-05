package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.ActorDTO;
import com.example.biblioteca.model.entity.Actor;

import java.util.List;

public interface IActorMapper {

    Actor dtoToEntity (ActorDTO actorDTO);
    ActorDTO entityToDto (Actor actor);
    List<ActorDTO> entityToDtoList (List<Actor> actors);
    List<Actor> dtoListToEntity (List<ActorDTO> actorsDTO);

}

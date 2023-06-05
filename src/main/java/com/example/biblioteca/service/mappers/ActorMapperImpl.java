package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.ActorDTO;
import com.example.biblioteca.model.entity.Actor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ActorMapperImpl implements IActorMapper {
    @Override
    public Actor dtoToEntity(ActorDTO actorDTO) {
        Actor actor = new Actor();
        actor.setId(actorDTO.getId());
        actor.setName(actorDTO.getName());
        actor.setSurnames(actorDTO.getSurnames());
        return actor;
    }

    @Override
    public ActorDTO entityToDto(Actor actor) {
        ActorDTO actordto = new ActorDTO();
        actordto.setId(actor.getId());
        actordto.setName(actor.getName());
        actordto.setSurnames(actor.getSurnames());
        return actordto;
    }

    @Override
    public List<ActorDTO> entityToDtoList(List<Actor> actors) {
        List<ActorDTO> actorsdto = new ArrayList<ActorDTO>();
        ActorDTO actordto = new ActorDTO();

        for (Actor a : actors) {
            actordto = entityToDto(a);
            actorsdto.add(actordto);
        }
        return actorsdto;
    }

    @Override
    public List<Actor> dtoListToEntity(List<ActorDTO> actorsDTO) {
        List<Actor> actors = new ArrayList<Actor>();
        Actor actor = new Actor();
        for (ActorDTO a : actorsDTO) {
            actor = dtoToEntity(a);
            actors.add(actor);
        }
        return actors;
    }
}

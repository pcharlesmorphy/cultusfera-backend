package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.MusicianDTO;
import com.example.biblioteca.model.entity.Musician;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MusicianMapperImpl implements IMusicianMapper{
    @Override
    public Musician dtoToEntity(MusicianDTO musicianDTO) {
        Musician Musician = new Musician();
        Musician.setId(musicianDTO.getId());
        Musician.setName(musicianDTO.getName());
        Musician.setSurnames(musicianDTO.getSurnames());
        return Musician;
    }

    @Override
    public MusicianDTO entityToDto(Musician musician) {
        MusicianDTO Musiciandto = new MusicianDTO();
        Musiciandto.setId(musician.getId());
        Musiciandto.setName(musician.getName());
        Musiciandto.setSurnames(musician.getSurnames());
        return Musiciandto;
    }

    @Override
    public List<MusicianDTO> entityToDtoList(List<Musician> musicians) {
        List<MusicianDTO> musiciansdto = new ArrayList<MusicianDTO>();
        MusicianDTO musiciandto = new MusicianDTO();

        for (Musician m:musicians){
            musiciandto = entityToDto(m);
            musiciansdto.add(musiciandto);
        }
        return musiciansdto;
    }

    @Override
    public List<Musician> dtoListToEntity(List<MusicianDTO> musiciansDTO) {
        List<Musician> musicians = new ArrayList<Musician>();
        Musician musician = new Musician();
        for (MusicianDTO m:musiciansDTO){
            musician = dtoToEntity(m);
            musicians.add(musician);
        }
        return musicians;
    }
}

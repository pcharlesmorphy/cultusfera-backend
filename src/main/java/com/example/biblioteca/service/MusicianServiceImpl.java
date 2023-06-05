package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Album;
import com.example.biblioteca.model.entity.Musician;
import com.example.biblioteca.model.entity.User;
import com.example.biblioteca.model.entity.Writer;
import com.example.biblioteca.repository.IAlbumRepository;
import com.example.biblioteca.repository.IMusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MusicianServiceImpl implements IMusicianService {
    @Autowired
    private IMusicianRepository musicianRepo;

    @Autowired
    private IAlbumRepository albumRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Musician> findAll() {
        return musicianRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Musician> findByName(String name, String surnames) {
        return musicianRepo.findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(name,surnames);
    }


    @Override
    public Optional<Musician> save(Musician musician) {
        if (!checkDuplicatedMusician(musician)){
            return Optional.of(  musicianRepo.save(musician));
        }
      return Optional.empty();
    }

    @Override
    public Optional<Musician> update(Musician musician) {
        if (!checkDuplicatedMusician(musician)){
            return Optional.of(  musicianRepo.save(musician));
        }
        return Optional.empty();
    }

    private Boolean checkDuplicatedMusician (Musician musician){
        List<Musician> musicians = findByName (musician.getName(),musician.getSurnames());
        if (musicians.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Musician> musician = findById(id);
        if (musician.isEmpty()) return false;

        // Si el musico tiene algún album en la base de datos no se puede eliminar
        List<Album> musicianAlbums = albumRepo.findAllByMusician(musician.get());
        if (!musicianAlbums.isEmpty()) return false;

        musicianRepo.deleteById(id);

        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Musician> findById(Long id) {
        return Optional.ofNullable(musicianRepo.findById(id)).orElse(null);
    }


}

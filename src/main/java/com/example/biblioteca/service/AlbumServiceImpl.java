package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.repository.IAlbumRepository;
import com.example.biblioteca.repository.IMusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements IAlbumService {

    @Autowired
    IAlbumRepository albumRepo;

    @Autowired
    IMusicianRepository musicianRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAll (){
        return albumRepo.findAll();
    }

    @Override
    @Transactional
    public Optional<Album> save(Album album){
        if (!checkDuplicatedAlbums(album)){
            album.setRating(0.0);
            album.setTotalReviews(0);
            return Optional.of(albumRepo.save(album));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Album> update (Album album){
        if (!checkDuplicatedAlbumsOnUpdate(album)){
            return Optional.of(albumRepo.save(album));
        }
        return Optional.empty();
    }

    private Boolean checkDuplicatedAlbumsOnUpdate (Album album){
        Album currentAlbum = findById(album.getId()).get();
        if (currentAlbum.getTitle().equalsIgnoreCase(album.getTitle())){
            if (currentAlbum.getRecordCompany().getName().equals(album.getRecordCompany().getName())) {
                if (currentAlbum.getMusician().getName().equals(album.getMusician().getName())) {
                    if (currentAlbum.getMusician().getSurnames().equals(album.getMusician().getSurnames())){
                        return false;
                    }
                }
            }
        }
        return checkDuplicatedAlbums(album);
    }

    private Boolean checkDuplicatedAlbums (Album album){

        List<Album> albums = albumRepo.findAllByTitleEqualsIgnoreCase(album.getTitle());

        if (albums.size() == 0){
            return false;
        }

        for (Album a:albums) {
            if (album.getRecordCompany().getName().equals(a.getRecordCompany().getName())) {
                if (album.getMusician().getName().equals(a.getMusician().getName())) {
                    if (album.getMusician().getSurnames().equals(a.getMusician().getSurnames())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    @Transactional
    public  Boolean delete(Long id){
        Optional<Album> album = findById(id);
        if (album.isEmpty()) return false;

        //Si el álbum tiene copias no se puede eliminar de la base de datos
        if (!album.get().getCopies().isEmpty()) return false;

        albumRepo.deleteById(id);
        return true;
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<Album> findById (Long id){
        return Optional.ofNullable(albumRepo.findById(id).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findByTitleContainsIgnoreCase(String title) {
        return albumRepo.findByTitleContainsIgnoreCase(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAllByMusicians(Musician musician) {
        return albumRepo.findAllByMusician(musician);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAlbumByMusician(String name, String surnames) {
        List<Musician> musicians = new ArrayList<Musician>();
        List<Album> albums = new ArrayList<Album>();

        if (surnames.isEmpty()){
            musicians=musicianRepo.findByNameContainingIgnoreCase(name);
            musicians.addAll(musicianRepo.findBySurnamesContainingIgnoreCase(name));

        } else {
            musicians=musicianRepo.findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(name,surnames);
        }

        for (Musician m:musicians){
            albums.addAll(albumRepo.findAllByMusician(m));
        }
        return albums;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAlbumByGenre(MusicGenre genre) {
        return albumRepo.findAllByGenre(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAllByGenre(MusicGenre genre){
        return albumRepo.findAllByGenre(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAllByMusician (Musician musician){
        return albumRepo.findAllByMusician(musician);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Album> findAllByRecordCompany(RecordCompany company) {
        return albumRepo.findAllByRecordCompany(company);
    }
}

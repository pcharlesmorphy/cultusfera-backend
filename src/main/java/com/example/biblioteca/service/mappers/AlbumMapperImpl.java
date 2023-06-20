package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.*;
import com.example.biblioteca.model.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumMapperImpl implements IAlbumMapper{

    @Autowired
    MusicianMapperImpl musicianMapper;

    @Autowired
    MusicGenreMapperImpl musicGenreMapper;

    @Autowired
    RecordCompanyMapperImpl recordCompanyMapper;

    @Autowired
    CopyMapperImpl copyMapper;

    @Autowired
    ReviewMapperImpl reviewMapper;

    @Override
    public Album dtoToEntity(AlbumDTO albumDTO) {
        Album album = new Album();
        album.setId(albumDTO.getId());
        album.setTitle(albumDTO.getTitle());
        album.setMusician(musicianMapper.dtoToEntity(albumDTO.getMusician()));
        album.setPublishedYear(albumDTO.getPublishedYear());
        album.setDuration(albumDTO.getDuration());
        album.setGenre(musicGenreMapper.dtoToEntity(albumDTO.getGenre()));
        album.setRecordCompany(recordCompanyMapper.dtoToEntity(albumDTO.getRecordCompany()));
        album.setType(albumDTO.getType());
        if (albumDTO.getCopies() != null) {
            album.setCopies(copyMapper.dtoListToEntity(albumDTO.getCopies()));
        }
        if (albumDTO.getReviews() != null){
            album.setReviews(reviewMapper.dtoListToEntity(albumDTO.getReviews()));
        }
        album.setRating(album.getRating());

        return album;
    }

    @Override
    public AlbumDTO entityToDto(Album album) {
        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setId(album.getId());
        albumDTO.setTitle(album.getTitle());
        albumDTO.setMusician(musicianMapper.entityToDto(album.getMusician()));
        albumDTO.setPublishedYear(album.getPublishedYear());
        albumDTO.setDuration(album.getDuration());
        albumDTO.setGenre(musicGenreMapper.entityToDto(album.getGenre()));
        albumDTO.setRecordCompany(recordCompanyMapper.entityToDto(album.getRecordCompany()));
        albumDTO.setType(album.getType());
        if (album.getCopies() != null){
            albumDTO.setCopies(copyMapper.entityToDtoList(album.getCopies()));
        }
        if (album.getReviews() != null){
            albumDTO.setReviews(reviewMapper.entityToDtoList(album.getReviews()));
        }
        albumDTO.setRating(album.getRating());
        return albumDTO;
    }

    @Override
    public List<AlbumDTO> entityToDtoList(List<Album> albums) {

        List<AlbumDTO> albumsDTO = new ArrayList<AlbumDTO>();
        for (Album a: albums){
            albumsDTO.add(entityToDto(a));
        }

        return albumsDTO;
    }
}

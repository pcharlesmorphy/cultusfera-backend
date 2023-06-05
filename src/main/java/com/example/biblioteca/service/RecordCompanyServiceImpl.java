package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Album;
import com.example.biblioteca.model.entity.RecordCompany;
import com.example.biblioteca.repository.IRecordCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecordCompanyServiceImpl implements IRecordCompanyService {
    @Autowired
    private IRecordCompanyRepository recordCompanyRepo;

    @Autowired
    private IAlbumService albumService;

    @Override
    @Transactional(readOnly = true)
    public List<RecordCompany> findAll(){
        return recordCompanyRepo.findAll();
    }

    @Override
    @Transactional
    public Optional<RecordCompany> save(RecordCompany company){
        if (!checkDuplicatedRecordCompany(company)){
            return Optional.of(recordCompanyRepo.save(company));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<RecordCompany> update(RecordCompany company){
        if (!checkDuplicatedRecordCompany(company)){
            return Optional.of(recordCompanyRepo.save(company));
        }
        return Optional.empty();
    }


    private Boolean checkDuplicatedRecordCompany (RecordCompany company) {
        RecordCompany recordCompany = recordCompanyRepo.findByNameEqualsIgnoreCase(company.getName());
        if (recordCompany == null){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(Long id){
        Optional<RecordCompany> company = findById(id);
        if (company.isEmpty()) return false;
        //Si la discográfica tiene algún álbum en la base de datos no se puede eliminar

        List<Album> companyAlbums = albumService.findAllByRecordCompany(company.get());
        if (!companyAlbums.isEmpty()) return false;

        recordCompanyRepo.deleteById(id);
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<RecordCompany> findById (Long id){
        return Optional.ofNullable(recordCompanyRepo.findById(id).orElse(null));
    }
}

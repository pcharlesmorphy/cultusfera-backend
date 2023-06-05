package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.MagazineSubject;
import com.example.biblioteca.repository.IMagazineSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MagazineSubjectServiceImpl implements IMagazineSubjectService {
    @Autowired
    private IMagazineSubjectRepository temaRevistaRepo;

    @Override
    @Transactional(readOnly = true)
    public List<MagazineSubject> findAll (){

        return temaRevistaRepo.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MagazineSubject> findById (Long id){
        return temaRevistaRepo.findById(id);
    }
}

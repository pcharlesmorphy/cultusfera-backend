package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Magazine;
import com.example.biblioteca.model.entity.MagazinePublisher;
import com.example.biblioteca.repository.IMagazinePublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MagazinePublisherServiceImpl implements IMagazinePublisherService {
    @Autowired
    private IMagazinePublisherRepository magazinePublisherRepo;

    @Autowired IMagazineService magazineService;

    @Override
    @Transactional(readOnly = true)
    public List<MagazinePublisher> findAll(){
        return magazinePublisherRepo.findAll();
    }

    @Override
    @Transactional
    public Optional<MagazinePublisher> save(MagazinePublisher publisher){
        if (!checkDuplicatedMagazinePublisher(publisher)){
            return Optional.of(magazinePublisherRepo.save(publisher));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<MagazinePublisher> update(MagazinePublisher publisher){
        if (!checkDuplicatedMagazinePublisher(publisher)){
            return Optional.of(magazinePublisherRepo.save(publisher));
        }
        return Optional.empty();
    }

    private Boolean checkDuplicatedMagazinePublisher (MagazinePublisher publisher){
        MagazinePublisher magazinePublisher = magazinePublisherRepo.findByNameEqualsIgnoreCase(publisher.getName());
        if (magazinePublisher == null) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(Long id){
        Optional<MagazinePublisher> publisher = findById(id);
        if (publisher.isEmpty()) return false;

        List<Magazine> publisherMagazines = magazineService.findAllByPublisher(publisher.get());
        if (!publisherMagazines.isEmpty()) return false;

        magazinePublisherRepo.deleteById(id);
        return true;
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<MagazinePublisher> findById (Long id){
        return Optional.ofNullable(magazinePublisherRepo.findById(id).orElse(null));
    }
}

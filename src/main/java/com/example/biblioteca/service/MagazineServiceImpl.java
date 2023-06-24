package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;
import com.example.biblioteca.repository.IMagazineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MagazineServiceImpl implements IMagazineService {

    @Autowired
    IMagazineRepository magazineRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Magazine> findAll() {
        return magazineRepo.findAll();
    }

    @Override
    @Transactional
    public Optional<Magazine> save(Magazine magazine) {
        if (!checkDuplicatedMagazines(magazine)){
            magazine.setRating(0.0);
            magazine.setTotalReviews(0);
            return Optional.of(magazineRepo.save(magazine));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Magazine> update(Magazine magazine) {
        if (!checkDuplicatedMagazinesOnUpdate(magazine)){
            return Optional.of(magazineRepo.save(magazine));
        }
        return Optional.empty();
    }

    private Boolean checkDuplicatedMagazinesOnUpdate (Magazine magazine){
        Magazine currentMagazine = findById(magazine.getId()).get();
        if (currentMagazine.getTitle().equalsIgnoreCase(magazine.getTitle())){
            if (magazine.getNumber().equals(magazine.getNumber())){
                return false;
            }
        }
        return checkDuplicatedMagazines(magazine);
    }

    private Boolean checkDuplicatedMagazines (Magazine magazine){

        List<Magazine> magazines = magazineRepo.findAllByTitleEqualsIgnoreCase(magazine.getTitle());

        if (magazines.size() == 0){
            return false;
        }

        for (Magazine m:magazines) {
            if (magazine.getNumber().equals(m.getNumber())){
                return true;
            }
        }
        return false;
    }


    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Magazine> magazine = findById(id);
        if (magazine.isEmpty()) return false;

        //Si la revista tiene copias no se puede eliminar de la base de datos
        if (!magazine.get().getCopies().isEmpty()) return false;

        magazineRepo.deleteById(id);
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Magazine> findById(Long id) {
        return Optional.ofNullable(magazineRepo.findById(id)).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Magazine> findBySubject(MagazineSubject subject) {
        return magazineRepo.findAllBySubject(subject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Magazine> findByTitleContainsIgnoreCase(String title) {
        return magazineRepo.findByTitleContainsIgnoreCase(title);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Magazine> findByLanguage(Language language) {
        return magazineRepo.findAllByLanguage(language);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Magazine> findAllByPublisher(MagazinePublisher publisher) {
        return magazineRepo.findAllByPublisher(publisher);
    }
}

package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Author;
import com.example.biblioteca.model.entity.Role;
import com.example.biblioteca.repository.IAuthorRepository;
import com.example.biblioteca.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private IAuthorRepository authorRepo;
    @Autowired
    private IRoleRepository roleRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Author> findAll (){
        return authorRepo.findAll();
    }

    @Override
    @Transactional
    public Author save(Author author){
        return authorRepo.save(author);
    }

    @Override
    @Transactional
    public  Boolean delete(Long id){
        if (findById(id).isEmpty()) return false;
        authorRepo.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Author update (Author author){
        return authorRepo.save(author);
    }

    @Override
    @Transactional
    public Optional<Author> findById (Long id){
        return Optional.ofNullable(authorRepo.findById(id).orElse(null));
    }

    @Override
    public List<Author> findAllByRole(String tipo){

        Role role = roleRepo.findRoleByType(tipo);
        return authorRepo.findAllByRole(role);
    }

}

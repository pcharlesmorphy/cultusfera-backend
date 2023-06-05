package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Book;
import com.example.biblioteca.model.entity.Writer;
import com.example.biblioteca.repository.IBookRepository;
import com.example.biblioteca.repository.IWriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WriterServiceImpl implements IWriterService {
    @Autowired
    private IWriterRepository writerRepo;

    @Autowired
    private IBookService bookService;

    @Override
    @Transactional(readOnly = true)
    public List<Writer> findAll() {
        return writerRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Writer> findByName(String name, String surnames) {
        return writerRepo.findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(name,surnames);
    }

    @Override
    @Transactional
    public Optional<Writer> save(Writer writer) {
        if (!checkDuplicatedWriter(writer)) {
            return Optional.of(writerRepo.save(writer));
        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Writer> update(Writer writer) {
        if (!checkDuplicatedWriter(writer)) {
            return Optional.of(writerRepo.save(writer));
        }

        return Optional.empty();
    }

    private Boolean checkDuplicatedWriter (Writer writer){
         List<Writer> writers = findByName(writer.getName(), writer.getSurnames());
         if (writers.isEmpty()){
             return false;
         }
         return true;
    }

    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Writer> writer = findById(id);
        if (writer.isEmpty()) return false;
        //Si el escritor tiene algún libro en la base de datos no se puede eliminar
        List<Book> writerBooks = bookService.findAllByWriters(writer.get());
        if (!writerBooks.isEmpty()) return false;
        writerRepo.deleteById(id);
        return true;
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Writer> findById(Long id) {
        return Optional.ofNullable(writerRepo.findById(id)).orElse(null);
    }


}

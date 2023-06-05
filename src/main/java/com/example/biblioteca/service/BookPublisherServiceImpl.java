package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.Book;
import com.example.biblioteca.model.entity.BookPublisher;
import com.example.biblioteca.repository.IBookPublisherRepository;
import com.example.biblioteca.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookPublisherServiceImpl implements IBookPublisherService {
    @Autowired
    private IBookPublisherRepository bookPublisherRepo;

    @Autowired
    private IBookService bookService;

    @Override
    @Transactional(readOnly = true)
    public List<BookPublisher> findAll(){
        return bookPublisherRepo.findAll();
    }

    @Override
    @Transactional
    public Optional<BookPublisher> save(BookPublisher publisher){
        if (!checkDuplicatedBookPublisher(publisher)){
            return Optional.of (bookPublisherRepo.save(publisher));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<BookPublisher> update(BookPublisher publisher){
        if (!checkDuplicatedBookPublisher(publisher)){
            return Optional.of (bookPublisherRepo.save(publisher));
        }
        return Optional.empty();
    }

    private Boolean checkDuplicatedBookPublisher (BookPublisher publisher){
        BookPublisher bookPublisher = bookPublisherRepo.findBookPublisherByName(publisher.getName());
        if (bookPublisher == null){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean delete(Long id){
        Optional<BookPublisher> publisher = findById(id);
        if (publisher.isEmpty()) return false;
        //Si la editorial tiene algún libro en la base de datos no se puede eliminar
        List<Book> publisherBooks = bookService.findBooksByPublisher(publisher.get());
        if(!publisherBooks.isEmpty()) return false;

        bookPublisherRepo.deleteById(id);
        return true;
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<BookPublisher> findById (Long id){
        return Optional.ofNullable(bookPublisherRepo.findById(id).orElse(null));
    }
}

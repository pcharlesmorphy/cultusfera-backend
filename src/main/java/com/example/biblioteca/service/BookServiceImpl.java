package com.example.biblioteca.service;

import com.example.biblioteca.model.entity.*;

import com.example.biblioteca.repository.IAuthorRepository;
import com.example.biblioteca.repository.IWriterRepository;
import com.example.biblioteca.repository.IBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookRepository bookRepo;

    @Autowired
    private IAuthorRepository authorRepo;

    @Autowired
    private IWriterRepository writerRepo;

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    @Override
    @Transactional
    public Optional<Book> save(Book book) {
        if (!checkDuplicatedBooks(book)){
            book.setRating(0.0);
            book.setTotalReviews(0);
            return Optional.of(bookRepo.save(book));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Book> update(Book book) {
        if (!checkDuplicatedBooksOnUpdate(book)){
            return Optional.of(bookRepo.save(book));
        }
        return Optional.empty();
    }

    private Boolean checkDuplicatedBooksOnUpdate (Book book){
        Book currentBook = findById(book.getId()).get();
        if (currentBook.getTitle().equalsIgnoreCase(book.getTitle())){
            if (currentBook.getPublisher().getName().equals(book.getPublisher().getName())) {
                if (book.getWriters().equals(book.getWriters())) {
                    return false;
                }
            }
        }
        return checkDuplicatedBooks(book);
    }
    private Boolean checkDuplicatedBooks (Book book){

        List<Book> books = bookRepo.findAllByTitleEqualsIgnoreCase(book.getTitle());

        if (books.size() == 0){
            return false;
        }

        for (Book b:books) {
            if (book.getPublisher().getName().equals(b.getPublisher().getName())) {
                if (book.getWriters().equals(b.getWriters())) {
                    return true;
                }
            }
        }
        return false;
    }



    @Override
    @Transactional
    public Boolean delete(Long id) {
        Optional<Book> book = findById(id);
        if (book.isEmpty()) return false;

        //Si el libro tiene copias no se puede eliminar de la base de datos
        if (!book.get().getCopies().isEmpty()) return false;

        bookRepo.deleteById(id);
        return true;
    }



    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(bookRepo.findById(id)).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findByTitleContainsIgnoreCase(String title){
        return bookRepo.findByTitleContainsIgnoreCase(title);
    }
    @Override
    @Transactional(readOnly = true)
    public List<Book> findByIsbnContainsIgnoreCase (String isbn){

        return bookRepo.findByIsbnContainsIgnoreCase(isbn);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAllByWriters(Writer writer) {

        return bookRepo.findAllByWriters(writer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBookByWriter(String name, String surnames) {
        List<Writer> writers = new ArrayList<Writer>();
        List<Book> books = new ArrayList<Book>();

        if (surnames.isEmpty()){
            writers=writerRepo.findByNameContainingIgnoreCase(name);
            writers.addAll(writerRepo.findBySurnamesContainingIgnoreCase(name));
        } else {
            writers=writerRepo.findByNameContainingIgnoreCaseAndSurnamesContainingIgnoreCase(name,surnames);
        }

        for (Writer w:writers){
            books.addAll(bookRepo.findAllByWriters(w));
        }
        return books;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBookByGenre(LiteraryGenre genre) {
        return bookRepo.findAllByGenre(genre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBookByLanguage(Language language) {
        return bookRepo.findAllByLanguage(language);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByPublisher(BookPublisher publisher) {
        return bookRepo.findAllByPublisher(publisher);
    }
}

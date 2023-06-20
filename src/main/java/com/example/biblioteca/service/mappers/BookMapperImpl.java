package com.example.biblioteca.service.mappers;

import com.example.biblioteca.model.dto.BookDTO;
import com.example.biblioteca.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapperImpl implements IBookMapper {

    @Autowired
    AuthorMapperImpl authorMapper;
    @Autowired
    WriterMapperImpl writerMapper;
    @Autowired
    BookPublisherMapperImpl bookPublisherMapper;
    @Autowired
    LiteraryGenreMapperImpl literaryGenreMapper;
    @Autowired
    LanguageMapperImpl languageMapper;
    @Autowired
    CopyMapperImpl copyMapper;

    @Autowired
    ReviewMapperImpl reviewMapper;

    @Override
    public Book dtoToEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setPublishedYear(bookDTO.getPublishedYear());
        book.setIsbn(bookDTO.getIsbn());
        book.setPublisher(bookPublisherMapper.dtoToEntity(bookDTO.getPublisher()));
        book.setGenre(literaryGenreMapper.dtoToEntity(bookDTO.getGenre()));
        book.setWriters(writerMapper.dtoListToEntity(bookDTO.getWriters()));
        book.setSynopsis(bookDTO.getSynopsis());
        book.setPages(bookDTO.getPages());
        book.setType(bookDTO.getType());
        book.setLanguage(languageMapper.dtoToEntity(bookDTO.getLanguage()));
        if (bookDTO.getCopies() != null) {
            book.setCopies(copyMapper.dtoListToEntity(bookDTO.getCopies()));
        }
        if (bookDTO.getReviews() != null){
            book.setReviews(reviewMapper.dtoListToEntity(bookDTO.getReviews()));
        }
        book.setRating(bookDTO.getRating());

        return book;
    }

    @Override
    public BookDTO entityToDto(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setPublishedYear(book.getPublishedYear());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPublisher(bookPublisherMapper.entityToDto(book.getPublisher()));
        bookDTO.setGenre(literaryGenreMapper.entityToDto(book.getGenre()));
        bookDTO.setPages(book.getPages());
        bookDTO.setSynopsis(book.getSynopsis());
        bookDTO.setWriters(writerMapper.entityToDtoList(book.getWriters()));
        bookDTO.setLanguage(languageMapper.entityToDto(book.getLanguage()));
        bookDTO.setType(book.getType());
        if (book.getCopies() != null){
            bookDTO.setCopies(copyMapper.entityToDtoList(book.getCopies()));
        }
        if (book.getReviews() != null){
            bookDTO.setReviews(reviewMapper.entityToDtoList(book.getReviews()));
        }

        bookDTO.setRating(book.getRating());

        return bookDTO;
    }

    @Override
    public List<BookDTO> entityToDtoList(List<Book> books) {

        List<BookDTO> booksDTO = new ArrayList<BookDTO>();
        for (Book b: books){
            booksDTO.add(entityToDto(b));
        }

        return booksDTO;
    }
}

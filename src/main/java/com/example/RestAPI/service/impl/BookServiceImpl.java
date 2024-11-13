package com.example.RestAPI.service.impl;

import com.example.RestAPI.dto.BookDTO;
import com.example.RestAPI.entity.BookEntity;
import com.example.RestAPI.exception.UnableToCreateBook;
import com.example.RestAPI.exception.UnableToFindTitle;
import com.example.RestAPI.repository.BookRepository;
import com.example.RestAPI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
   public BookServiceImpl(final BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    BookEntity bookDTOToBookEntity(BookDTO book){
        return BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor()).build();
    }

    BookDTO bookEntityToBookDTO(BookEntity bookEntity){
        return BookDTO.builder()
                .Isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor()).build();
    }

    @Override
    public BookEntity create(BookDTO bookDTO) {
        try{
            bookRepository.save(bookDTOToBookEntity(bookDTO));
            return BookEntity.builder().isbn(bookDTO.getIsbn())
                    .title(bookDTO.getTitle())
                    .author(bookDTO.getAuthor()).build();
        } catch (RuntimeException e) {
            throw new UnableToCreateBook("Unable to Create book","Unable to create book");
        }

    }

    @Override
    public List<BookDTO> findByTitile(String title) {
        try{
        List<BookEntity> books = bookRepository.findByTitleContaining(title);
        return books.stream().map(this::bookEntityToBookDTO).collect(Collectors.toList());}
        catch (Exception e){throw new UnableToFindTitle("Unable to find given title name","Unable to find book using given title");
        }
    }

    @Override
    public List<BookDTO> showAllBooks() {
        List<BookEntity> allBooks=bookRepository.findAll();
        return allBooks.stream().map(this::bookEntityToBookDTO).collect(Collectors.toList());
    }

}

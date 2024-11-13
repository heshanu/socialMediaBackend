package com.example.RestAPI.service.impl;

import com.example.RestAPI.dto.BookDTO;
import com.example.RestAPI.entity.BookEntity;
import com.example.RestAPI.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    BookDTO bookDTO=BookDTO.builder()
            .Isbn("12345")
            .title("Test")
            .author("Test").build();

    BookEntity bookEntity=BookEntity.builder()
            .Isbn("12345")
            .title("Test")
            .author("Test").build();


}
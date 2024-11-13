package com.example.RestAPI.service;

import com.example.RestAPI.dto.BookDTO;
import com.example.RestAPI.entity.BookEntity;
import java.util.List;

public interface BookService {
    BookEntity create(BookDTO bookDTO);
    List<BookDTO> findByTitile(String title);
    List<BookDTO> showAllBooks();
}

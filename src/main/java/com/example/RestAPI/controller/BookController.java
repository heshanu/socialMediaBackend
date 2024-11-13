package com.example.RestAPI.controller;

import com.example.RestAPI.dto.BookDTO;
import com.example.RestAPI.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/book/")
public class BookController {
    @Autowired
    private BookService bookService;

    @PostMapping("")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO){
        this.bookService.create(bookDTO);
        return new ResponseEntity<>(bookDTO, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<BookDTO>> showAllBooks(){
        List<BookDTO> bookList=this.bookService.showAllBooks();
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }

    @GetMapping("title")
    public ResponseEntity<List<BookDTO>> showBooksByTitle(@RequestParam String title){
        List<BookDTO> bookList=this.bookService.findByTitile(title);
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }
}

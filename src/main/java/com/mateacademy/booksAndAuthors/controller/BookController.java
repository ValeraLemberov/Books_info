package com.mateacademy.booksAndAuthors.controller;

import com.mateacademy.booksAndAuthors.dto.BookRequestDto;
import com.mateacademy.booksAndAuthors.dto.BookResponseDto;
import com.mateacademy.booksAndAuthors.dto.mapper.BookMapper;
import com.mateacademy.booksAndAuthors.service.BookService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService,
                          BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PutMapping
    public BookResponseDto create(@RequestBody BookRequestDto bookRequestDto) {
        return bookMapper
                .toResponseDto(bookService
                        .create(bookMapper
                                .toModel(bookRequestDto)));
    }

    @PutMapping
    public BookResponseDto update(@RequestBody BookRequestDto bookRequestDto,
                                  @RequestParam Long bookId) {
        return bookMapper
                .toResponseDto(bookService
                        .update(bookMapper
                                .toModel(bookRequestDto), bookId));
    }

    @DeleteMapping
    public void delete(@RequestParam Long bookId) {
        bookService.delete(bookId);
    }
}

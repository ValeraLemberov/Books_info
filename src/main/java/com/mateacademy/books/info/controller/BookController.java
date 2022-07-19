package com.mateacademy.books.info.controller;

import com.mateacademy.books.info.dto.BookRequestDto;
import com.mateacademy.books.info.dto.BookResponseDto;
import com.mateacademy.books.info.dto.mapper.BookMapper;
import com.mateacademy.books.info.model.Book;
import com.mateacademy.books.info.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService,
                          BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    @ApiOperation(value = "Add new book to the database")
    public BookResponseDto create(@RequestBody BookRequestDto bookRequestDto) {
        return bookMapper
                .toResponseDto(bookService
                        .create(bookMapper
                                .toModel(bookRequestDto)));
    }

    @PutMapping
    @ApiOperation(value = "Update book by id")
    public BookResponseDto update(@RequestBody BookRequestDto bookRequestDto,
                                  @RequestParam(name = "id")
                                  @ApiParam(value = "Insert book id") Long id) {
        Book book = bookMapper.toModel(bookRequestDto);
        book.setId(id);
        return bookMapper.toResponseDto(bookService.update(book));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete book by id")
    public void delete(@RequestParam(name = "id")
                       @ApiParam(value = "Insert book id") Long id) {
        bookService.delete(id);
    }

    @GetMapping("/bestseller")
    @ApiOperation(value = "Get bestseller bu the author's name")
    public BookResponseDto getMostSellingBook(@RequestParam(name = "by-author")
                                              @ApiParam(value = "Insert author name")
                                              String name) {
        return bookMapper.toResponseDto(bookService.getBestSellingBook(name));
    }

    @GetMapping
    @ApiOperation(value = "Get all books by the author's name")
    public List<BookResponseDto> getAllBooksByAuthorName(@RequestParam(name = "by-author")
                                                         @ApiParam(value = "Insert author name")
                                                         String name) {

        return bookService.getAllByAuthorName(name)
                .stream()
                .map(bookMapper::toResponseDto).toList();
    }

    @GetMapping("/most-published")
    @ApiOperation(value = "Get the most published books by the author's name")
    public BookResponseDto getMostPublishedBook(@RequestParam(name = "by-author")
                                                @ApiParam(value = "Insert author name")
                                                String name) {
        return bookMapper.toResponseDto(bookService.getMostPublishedBook(name));
    }

    @GetMapping("/bestsellers")
    @ApiOperation(value = "Get all top-selling books by the author name")
    public List<BookResponseDto> getBestSellingBook(@RequestParam(name = "by-author")
                                                    @ApiParam(value = "Insert author name")
                                                                String name) {
        return bookService.getBestSellingBooks(name)
                .stream()
                .map(bookMapper::toResponseDto)
                .toList();
    }

    @GetMapping("/list-most-published")
    @ApiOperation(value = "Get all top-published books by the author's nane")
    public List<BookResponseDto> getMostPublishedBooks(@RequestParam(name = "by-author")
                                                           @ApiParam(value = "Insert author name")
                                                                   String name) {
        return bookService.getMostPublishedBooks(name)
                .stream()
                .map(bookMapper::toResponseDto).toList();
    }

    @GetMapping("/successful")
    @ApiOperation(value = "Get top-successful books by the author's name")
    public List<BookResponseDto> getMostSuccessful(@RequestParam(name = "by-author")
                                                       @ApiParam(value = "Insert author name")
                                                               String name) {
        return bookService.getMostSuccessfulBooks(name)
                .stream()
                .map(bookMapper::toResponseDto).toList();
    }
}

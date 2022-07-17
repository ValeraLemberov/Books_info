package com.mateacademy.books.info.controller;

import com.mateacademy.books.info.dto.AuthorResponseDto;
import com.mateacademy.books.info.dto.BookRequestDto;
import com.mateacademy.books.info.dto.BookResponseDto;
import com.mateacademy.books.info.dto.mapper.AuthorMapper;
import com.mateacademy.books.info.dto.mapper.BookMapper;
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
    private final AuthorMapper authorMapper;

    public BookController(BookService bookService,
                          BookMapper bookMapper,
                          AuthorMapper authorMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
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
                                  @RequestParam(name = "book-id")
                                  @ApiParam(value = "Insert book id") Long bookId) {
        return bookMapper
                .toResponseDto(bookService
                        .update(bookMapper
                                .toModel(bookRequestDto), bookId));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete book by id")
    public void delete(@RequestParam(name = "book-id")
                       @ApiParam(value = "Insert book id") Long bookId) {
        bookService.delete(bookId);
    }

    @GetMapping("/most-selling")
    @ApiOperation(value = "Get bestseller bu the author's name")
    public BookResponseDto getMostSellingBook(@RequestParam(name = "author-name")
                                              @ApiParam(value = "Insert author name")
                                              String authorName) {
        return bookMapper.toResponseDto(bookService.getBestSellingBook(authorName));
    }

    @GetMapping("/all-books")
    @ApiOperation(value = "Get all books by the author's name")
    public List<BookResponseDto> getAllBooksByAuthorName(@RequestParam(name = "author-name")
                                                         @ApiParam(value = "Insert author name")
                                                         String authorName) {

        return bookService.getAllByAuthorName(authorName)
                .stream()
                .map(bookMapper::toResponseDto).toList();
    }

    @GetMapping("/most-published")
    @ApiOperation(value = "Get the most published books by the author's name")
    public BookResponseDto getMostPublishedBook(@RequestParam(name = "author-name")
                                                @ApiParam(value = "Insert author name")
                                                String authorName) {
        return bookMapper.toResponseDto(bookService.getMostPublishedBook(authorName));
    }

    @GetMapping("/list-best-selling")
    @ApiOperation(value = "Get all top-selling books by the author name")
    public List<BookResponseDto> getBestSellingBook(@RequestParam(name = "author-name")
                                                    @ApiParam(value = "Insert author name")
                                                                String authorName) {
        return bookService.getBestSellingBooks(authorName)
                .stream()
                .map(bookMapper::toResponseDto)
                .toList();
    }

    @GetMapping("/list-most-published")
    @ApiOperation(value = "Get all top-published books by the author's nane")
    public List<BookResponseDto> getMostPublishedBooks(@RequestParam(name = "author-name")
                                                           @ApiParam(value = "Insert author name")
                                                                   String authorName) {
        return bookService.getMostPublishedBooks(authorName)
                .stream()
                .map(bookMapper::toResponseDto).toList();
    }

    @GetMapping("/successful-book")
    @ApiOperation(value = "Get top-successful books by the author's name")
    public List<BookResponseDto> getMostSuccessful(@RequestParam(name = "author-name")
                                                       @ApiParam(value = "Insert author name")
                                                               String authorName) {
        return bookService.getMostSuccessfulBooks(authorName)
                .stream()
                .map(bookMapper::toResponseDto).toList();
    }

    @GetMapping("/successful-author")
    @ApiOperation(value = "Get the most successful author")
    public AuthorResponseDto getSuccessfulAuthor() {
        return authorMapper.toResponseDto(bookService.findMostSuccessfulAuthor());
    }
}

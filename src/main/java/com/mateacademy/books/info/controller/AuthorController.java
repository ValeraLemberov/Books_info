package com.mateacademy.books.info.controller;

import com.mateacademy.books.info.dto.AuthorRequestDto;
import com.mateacademy.books.info.dto.AuthorResponseDto;
import com.mateacademy.books.info.dto.mapper.AuthorMapper;
import com.mateacademy.books.info.model.Author;
import com.mateacademy.books.info.service.AuthorService;
import com.mateacademy.books.info.service.BookService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;
    private final BookService bookService;

    public AuthorController(AuthorService authorService,
                            AuthorMapper authorMapper,
                            BookService bookService) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
        this.bookService = bookService;
    }

    @PostMapping
    @ApiOperation(value = "Add new author to the database")
    public AuthorResponseDto create(@RequestBody AuthorRequestDto authorRequestDto) {
        return authorMapper
                .toResponseDto(authorService
                .create(authorMapper
                .toModel(authorRequestDto)));
    }

    @PutMapping
    @ApiOperation(value = "Update the author by id")
    public AuthorResponseDto update(@RequestBody AuthorRequestDto authorRequestDto,
                                    @RequestParam(name = "id")
                                    @ApiParam(value = "Insert author id") Long id) {
        Author author = authorMapper.toModel(authorRequestDto);
        author.setId(id);
        return authorMapper.toResponseDto(authorService.update(author));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete author by id")
    public void delete(@RequestParam(name = "id")
                       @ApiParam(value = "Insert author id") Long id) {
        authorService.delete(id);
    }

    @GetMapping("/successful")
    @ApiOperation(value = "Get the most successful author")
    public AuthorResponseDto getSuccessfulAuthor() {
        return authorMapper.toResponseDto(bookService.findMostSuccessfulAuthor());
    }
}

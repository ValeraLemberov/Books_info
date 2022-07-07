package com.mateacademy.booksAndAuthors.controller;

import com.mateacademy.booksAndAuthors.dto.AuthorRequestDto;
import com.mateacademy.booksAndAuthors.dto.AuthorResponseDto;
import com.mateacademy.booksAndAuthors.dto.mapper.AuthorMapper;
import com.mateacademy.booksAndAuthors.service.AuthorService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    public AuthorController(AuthorService authorService,
                            AuthorMapper authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PutMapping
    public AuthorResponseDto create(@RequestBody AuthorRequestDto authorRequestDto){
        return authorMapper
                .toResponseDto(authorService
                .create(authorMapper
                .toModel(authorRequestDto)));
    }

    @PutMapping
    public AuthorResponseDto update(@RequestBody AuthorRequestDto authorRequestDto,
                                    @RequestParam Long authorId){
        return authorMapper
                .toResponseDto(authorService
                .update(authorMapper
                .toModel(authorRequestDto), authorId));
    }

    @DeleteMapping
    public void delete(@RequestParam Long authorId){
        authorService.delete(authorId);
    }
}

package com.mateacademy.booksAndAuthors.dto.mapper;

import com.mateacademy.booksAndAuthors.dto.AuthorRequestDto;
import com.mateacademy.booksAndAuthors.dto.AuthorResponseDto;
import com.mateacademy.booksAndAuthors.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toModel(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setAuthorName(author.getAuthorName());
        author.setBirthDate(authorRequestDto.getBirthDate());
        author.setEmail(authorRequestDto.getEmail());
        author.setPhone(authorRequestDto.getPhone());
        return author;
    }

    public AuthorResponseDto toResponseDto(Author author) {
        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setId(author.getId());
        authorResponseDto.setEmail(author.getEmail());
        authorResponseDto.setAuthorName(author.getAuthorName());
        authorResponseDto.setPhone(author.getPhone());
        authorResponseDto.setBirthDate(author.getBirthDate());
        return authorResponseDto;
    }
}

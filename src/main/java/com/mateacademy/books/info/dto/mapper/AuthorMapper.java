package com.mateacademy.books.info.dto.mapper;

import com.mateacademy.books.info.dto.AuthorRequestDto;
import com.mateacademy.books.info.dto.AuthorResponseDto;
import com.mateacademy.books.info.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toModel(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        author.setAuthorName(authorRequestDto.getAuthorName());
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

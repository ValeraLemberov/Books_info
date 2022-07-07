package com.mateacademy.booksAndAuthors.dto.mapper;

import com.mateacademy.booksAndAuthors.dto.BookRequestDto;
import com.mateacademy.booksAndAuthors.dto.BookResponseDto;
import com.mateacademy.booksAndAuthors.model.Author;
import com.mateacademy.booksAndAuthors.model.Book;
import com.mateacademy.booksAndAuthors.service.AuthorService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    private final AuthorService authorService;

    public BookMapper(AuthorService authorService) {
        this.authorService = authorService;
    }


    public Book toModel(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setBookName(book.getBookName());
        book.setPublishedAmount(book.getPublishedAmount());
        book.setSoldAmount(book.getSoldAmount());
        book.setAuthors(bookRequestDto.getAuthorId()
                .stream().map(authorService::findById)
                .collect(Collectors.toSet()));
        return book;
    }

    public BookResponseDto toResponseDto(Book book) {
        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setId(book.getId());
        bookResponseDto.setBookName(book.getBookName());
        bookResponseDto.setSoldAmount(book.getSoldAmount());
        bookResponseDto.setPublishedAmount(book.getPublishedAmount());
        bookResponseDto.setAuthorId(book.getAuthors()
                .stream()
                .map(Author::getId)
                .collect(Collectors.toSet()));
        return bookResponseDto;
    }
}

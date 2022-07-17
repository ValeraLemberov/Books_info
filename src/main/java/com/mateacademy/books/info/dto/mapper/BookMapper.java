package com.mateacademy.books.info.dto.mapper;

import com.mateacademy.books.info.dto.BookRequestDto;
import com.mateacademy.books.info.dto.BookResponseDto;
import com.mateacademy.books.info.model.Author;
import com.mateacademy.books.info.model.Book;
import com.mateacademy.books.info.service.AuthorService;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    private final AuthorService authorService;

    public BookMapper(AuthorService authorService) {
        this.authorService = authorService;
    }

    public Book toModel(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setBookName(bookRequestDto.getBookName());
        book.setPublishedAmount(bookRequestDto.getPublishedAmount());
        book.setSoldAmount(bookRequestDto.getSoldAmount());
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

package com.mateacademy.books.info.service;

import com.mateacademy.books.info.model.Author;
import com.mateacademy.books.info.model.Book;
import java.util.List;

public interface BookService extends GenericService<Book> {

    List<Book> getAllByAuthorName(String authorName);

    Book getBestSellingBook(String authorName);

    Book getMostPublishedBook(String authorName);

    List<Book> getBestSellingBooks(String authorName);

    List<Book> getMostPublishedBooks(String authorName);

    List<Book> getMostSuccessfulBooks(String authorsName);

    Author findMostSuccessfulAuthor();
}

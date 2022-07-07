package com.mateacademy.booksAndAuthors.service;

import com.mateacademy.booksAndAuthors.model.Book;

import java.util.List;

public interface BookService extends GenericService<Book>{

    public List<Book> getAllByAuthorName(String authorName);

    public Book findMostSellingBookByAuthor(String authorName);

    public Book findMostPublishedBootByAuthor(String authorName);

    public List<Book> findThreeMostSellingBooksAndAllAuthor(String authorName);

    public List<Book> findThreeMostPublishedBooksAndAllAuthors(String authorName);

    public List<Book> findMostSuccessfulBookByAuthor(String authorName);
}

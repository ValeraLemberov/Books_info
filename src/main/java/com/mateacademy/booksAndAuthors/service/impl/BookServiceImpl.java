package com.mateacademy.booksAndAuthors.service.impl;

import com.mateacademy.booksAndAuthors.model.Book;
import com.mateacademy.booksAndAuthors.repository.BookRepository;
import com.mateacademy.booksAndAuthors.service.BookService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllByAuthorName(String authorName) {
        return null;
    }

    @Override
    public Book findMostSellingBookByAuthor(String authorName) {
        return null;
    }

    @Override
    public Book findMostPublishedBootByAuthor(String authorName) {
        return null;
    }

    @Override
    public List<Book> findThreeMostSellingBooksAndAllAuthor(String authorName) {
        return null;
    }

    @Override
    public List<Book> findThreeMostPublishedBooksAndAllAuthors(String authorName) {
        return null;
    }

    @Override
    public List<Book> findMostSuccessfulBookByAuthor(String authorName) {
        return null;
    }

    @Override
    public Book create(Book model) {
        return bookRepository.save(model);
    }

    @Override
    public Book update(Book model, Long modelId) {
        model.setId(modelId);
        return bookRepository.save(model);
    }

    @Override
    public void delete(Long modelId) {
        bookRepository.deleteById(modelId);
    }
}

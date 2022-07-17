package com.mateacademy.books.info.service.impl;

import com.mateacademy.books.info.model.Author;
import com.mateacademy.books.info.model.Book;
import com.mateacademy.books.info.repository.BookRepository;
import com.mateacademy.books.info.service.BookService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllByAuthorName(String authorName) {
        return bookRepository.findAllBooksByAuthorName(authorName);
    }

    @Override
    public Book getBestSellingBook(String authorName) {
        return bookRepository.findBestSoldAmount(authorName);
    }

    @Override
    public Book getMostPublishedBook(String authorName) {
        return bookRepository.findMostPublishedAmount(authorName);
    }

    @Override
    public List<Book> getBestSellingBooks(String authorName) {
        return bookRepository.findAllBySoldAmount(authorName);
    }

    @Override
    public List<Book> getMostPublishedBooks(String authorName) {
        return bookRepository.findAllByPublishedAmount(authorName);
    }

    @Override
    public List<Book> getMostSuccessfulBooks(String authorsName) {
        return bookRepository.findMostSuccessfulBooks(authorsName);
    }

    @Override
    public Author findMostSuccessfulAuthor() {
        return bookRepository.findMostSuccessfulAuthor();
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

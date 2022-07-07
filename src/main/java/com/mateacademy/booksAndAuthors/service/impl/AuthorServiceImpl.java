package com.mateacademy.booksAndAuthors.service.impl;

import com.mateacademy.booksAndAuthors.model.Author;
import com.mateacademy.booksAndAuthors.repository.AuthorRepository;
import com.mateacademy.booksAndAuthors.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findMostSuccessfulAuthor() {
        return null;
    }

    @Override
    public Author findById(Long authorId) {
        return authorRepository.findById(authorId).get();
    }

    @Override
    public Author create(Author model) {
        return authorRepository.save(model);
    }

    @Override
    public Author update(Author model, Long modelId) {
        model.setId(modelId);
        return authorRepository.save(model);
    }

    @Override
    public void delete(Long modelId) {
        authorRepository.deleteById(modelId);
    }
}

package com.mateacademy.books.info.service.impl;

import com.mateacademy.books.info.model.Author;
import com.mateacademy.books.info.repository.AuthorRepository;
import com.mateacademy.books.info.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findById(Long authorId) {
        return authorRepository.getById(authorId);
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

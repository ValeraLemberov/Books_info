package com.mateacademy.booksAndAuthors.service;

import com.mateacademy.booksAndAuthors.model.Author;

public interface AuthorService extends GenericService<Author>{

    public Author findMostSuccessfulAuthor();

    public Author findById(Long authorId);
}

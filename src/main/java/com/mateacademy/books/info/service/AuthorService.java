package com.mateacademy.books.info.service;

import com.mateacademy.books.info.model.Author;

public interface AuthorService extends GenericService<Author> {

    Author findById(Long authorId);
}

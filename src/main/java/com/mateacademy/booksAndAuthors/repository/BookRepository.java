package com.mateacademy.booksAndAuthors.repository;

import com.mateacademy.booksAndAuthors.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}

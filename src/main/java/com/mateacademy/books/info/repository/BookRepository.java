package com.mateacademy.books.info.repository;

import com.mateacademy.books.info.model.Author;
import com.mateacademy.books.info.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("FROM Book b JOIN b.authors ba WHERE ba.name = :authorName")
    List<Book> findAllBooksByAuthorName(String authorName);

    @Query("FROM Book b JOIN b.authors ba "
            + "WHERE b.soldAmount = (SELECT MAX (b.soldAmount) "
            + "FROM Book b JOIN b.authors ba WHERE ba.name = :authorName) "
            + "AND ba.name = :authorName")
    Book findBestSoldAmount(String authorName);

    @Query("FROM Book b JOIN b.authors ba "
            + "WHERE b.publishedAmount = (SELECT MAX(b.publishedAmount) "
            + "FROM Book b JOIN b.authors a WHERE a.name = :authorName) "
            + "AND ba.name = :authorName")
    Book findMostPublishedAmount(String authorName);

    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors ba "
            + "WHERE b.soldAmount IN (SELECT MAX (b.soldAmount ) "
            + "FROM Book b JOIN b.authors ba WHERE ba.name "
            + "LIKE CONCAT('%', :authorName, '%')) "
            + "AND  ba.name LIKE CONCAT('%', :authorName, '%')")
    List<Book> findAllBySoldAmount(String authorName);

    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors ba "
            + "WHERE b.publishedAmount IN (SELECT MAX(b.publishedAmount) "
            + "FROM Book b JOIN b.authors ba WHERE ba.name "
            + "LIKE CONCAT('%', :authorName, '%')) " + "AND ba.name "
            + "LIKE CONCAT('%', :authorName, '%')")
    List<Book> findAllByPublishedAmount(String authorName);

    @Query("FROM Book b JOIN b.authors ba WHERE (b.soldAmount/b.publishedAmount) IN "
            + "(SELECT MAX(b.soldAmount/b.publishedAmount) FROM Book b JOIN b.authors ba "
            + "WHERE ba.name LIKE CONCAT('%',:authorName,'%')) "
            + "AND  ba.name LIKE CONCAT('%',:authorName,'%')")
    List<Book> findMostSuccessfulBooks(String authorName);

    @Query("SELECT ba FROM Book b JOIN b.authors ba"
            + " WHERE (b.soldAmount/b.publishedAmount/b.authors.size) = "
            + "(SELECT MAX(b.soldAmount/b.publishedAmount/b.authors.size) "
            + "FROM Book b JOIN b.authors)")
    Author findMostSuccessfulAuthor();
}

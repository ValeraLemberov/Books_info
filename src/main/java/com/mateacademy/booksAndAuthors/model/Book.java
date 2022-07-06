package com.mateacademy.booksAndAuthors.model;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "author_id")
    private Long authorId;
    @Column(name = "published_amount")
    private Integer publishedAmount;
    @Column(name = "sold_amount")
    private Integer soldAmount;
}

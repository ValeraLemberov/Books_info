package com.mateacademy.booksAndAuthors.dto;


import lombok.Data;

import java.util.Set;

@Data
public class BookRequestDto {
    private String bookName;
    private Set<Long> authorId;
    private Integer publishedAmount;
    private Integer soldAmount;
}

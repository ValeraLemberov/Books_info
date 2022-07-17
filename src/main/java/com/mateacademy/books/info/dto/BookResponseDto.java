package com.mateacademy.books.info.dto;

import java.util.Set;
import lombok.Data;

@Data
public class BookResponseDto {
    private Long id;
    private String bookName;
    private Set<Long> authorId;
    private Integer publishedAmount;
    private Integer soldAmount;
}

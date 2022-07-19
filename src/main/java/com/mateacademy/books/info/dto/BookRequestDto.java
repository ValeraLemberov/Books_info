package com.mateacademy.books.info.dto;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequestDto {
    private String title;
    private Set<Long> authorId;
    private Integer publishedAmount;
    private Integer soldAmount;
}

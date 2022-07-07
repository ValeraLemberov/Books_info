package com.mateacademy.booksAndAuthors.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AuthorRequestDto {
    private String authorName;
    private LocalDate birthDate;
    private String phone;
    private String email;
}

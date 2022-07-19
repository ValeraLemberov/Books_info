package com.mateacademy.books.info.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequestDto {
    private String name;
    private LocalDate birthDate;
    private String phone;
    private String email;
}

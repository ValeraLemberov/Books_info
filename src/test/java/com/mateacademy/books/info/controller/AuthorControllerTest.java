package com.mateacademy.books.info.controller;

import com.mateacademy.books.info.dto.AuthorRequestDto;
import com.mateacademy.books.info.model.Author;
import com.mateacademy.books.info.service.AuthorService;
import com.mateacademy.books.info.dto.mapper.AuthorMapper;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.time.LocalDate;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {
    private final AuthorMapper authorMapper;
    private final MockMvc mockMvc;
    private Author mockAuthor;
    private AuthorRequestDto authorRequestDto;
    private Author author;


    @Autowired
    AuthorControllerTest(AuthorMapper authorMapper,
                         MockMvc mockMvc) {
        this.authorMapper = authorMapper;
        this.mockMvc = mockMvc;
    }

    @MockBean
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        mockAuthor = new Author(null, "Charles Michael",
                LocalDate. of(1962, 11, 21),
                "+546487921", "palahniuk@gmail.com");
        authorRequestDto = new AuthorRequestDto(mockAuthor.getAuthorName(),
                mockAuthor.getBirthDate(), mockAuthor.getPhone(),
                mockAuthor.getEmail());
        author = new Author(1L, "Charles Michael",
                LocalDate. of(1962, 11, 21),
                "+546487921", "palahniuk@gmail.com");
    }

    @Test
    void createAuthor(){
        Mockito.when(authorService.create(mockAuthor)).thenReturn(author);
        RestAssuredMockMvc
                .given().contentType(ContentType.JSON).body(authorRequestDto)
                .when().post("/authors")
                .then().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("authorName", Matchers.equalTo("Charles Michael"))
                .body("birthDate.toString()", Matchers.equalTo("1962-11-21"))
                .body("phone", Matchers.equalTo("+546487921"))
                .body("email", Matchers.equalTo("palahniuk@gmail.com"));
    }

    @Test
    void updateAuthor(){
        mockAuthor.setAuthorName("Arkadii Natanovich");
        author.setAuthorName("Arkadii Natanovich");
        authorRequestDto.setAuthorName("Arkadii Natanovich");
        Mockito.when(authorService.update(mockAuthor, 1L)).thenReturn(author);
        RestAssuredMockMvc
                .given().contentType(ContentType.JSON).body(authorRequestDto)
                .queryParam("author-id", 1L)
                .when().put("/authors")
                .then().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("authorName", Matchers.equalTo("Arkadii Natanovich"))
                .body("birthDate.toString()", Matchers.equalTo("1962-11-21"))
                .body("phone", Matchers.equalTo("+546487921"))
                .body("email", Matchers.equalTo("palahniuk@gmail.com"));
    }
}
package com.mateacademy.books.info.controller;

import com.mateacademy.books.info.dto.BookRequestDto;
import com.mateacademy.books.info.dto.mapper.AuthorMapper;
import com.mateacademy.books.info.dto.mapper.BookMapper;
import com.mateacademy.books.info.model.Author;
import com.mateacademy.books.info.model.Book;
import com.mateacademy.books.info.service.AuthorService;
import com.mateacademy.books.info.service.BookService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
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
class BookControllerTest {
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;
    private final MockMvc mockMvc;

    @Autowired
    BookControllerTest(BookMapper bookMapper,
                       AuthorMapper authorMapper,
                       MockMvc mockMvc) {
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
        this.mockMvc = mockMvc;
    }

    @MockBean
    private BookService bookService;
    @MockBean
    private AuthorService authorService;
    private Book book1;
    private Book book2;
    private Book book3;
    private Author author1;
    private Book mockBook;
    private BookRequestDto bookRequestDto;

    @BeforeEach
    void setUp(){
        RestAssuredMockMvc.mockMvc(mockMvc);
        author1 = new Author(1L, "H. P. Lovecraft",
                LocalDate.of(1993,11,5), "42747583", "howard@gmail.com");
        book1 = new Book(1L, "Harry Potter", Set.of(author1), 2022123, 21314234);
        book2 = new Book(2L, "House of dragon", Set.of(author1), 1123124, 23441232);
        book3 = new Book(3L, "Twilight", Set.of(author1), 12313123, 34234);
        bookRequestDto =
                new BookRequestDto(book1.getTitle(),
                        Set.of(1L),book1.getPublishedAmount(), book1.getSoldAmount());
        mockBook = new Book(null, "Harry Potter", Set.of(author1), 2022123, 21314234);
    }

    @Test
    void showAllBooksByAuthorName(){
        List<Book> mockBooks = List.of(book1, book2, book3);
        Mockito.when(bookService.getAllByAuthorName("H. P. Lovecraft")).thenReturn(mockBooks);
        Mockito.when(authorService.findById(1L)).thenReturn(author1);
        RestAssuredMockMvc.given()
                .queryParam("by-author", "H. P. Lovecraft")
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].title", Matchers.equalTo("Harry Potter"))
                .body("[0].publishedAmount", Matchers.equalTo(2022123))
                .body("[0].soldAmount", Matchers.equalTo(21314234))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].title", Matchers.equalTo("House of dragon"))
                .body("[1].publishedAmount", Matchers.equalTo(1123124))
                .body("[1].soldAmount", Matchers.equalTo(23441232))
                .body("[2].id", Matchers.equalTo(3))
                .body("[2].title", Matchers.equalTo("Twilight"))
                .body("[2].publishedAmount", Matchers.equalTo(12313123))
                .body("[2].soldAmount", Matchers.equalTo(34234));
    }

    @Test
    void createNewBook(){
        Mockito.when(bookService.create(mockBook)).thenReturn(book1);
        Mockito.when(authorService.findById(1L)).thenReturn(author1);
        RestAssuredMockMvc
                .given()
                .contentType(ContentType.JSON)
                .body(bookRequestDto)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .body("id",Matchers.equalTo(1))
                .body("title",Matchers.equalTo("Harry Potter"))
                .body("publishedAmount",Matchers.equalTo(2022123))
                .body("soldAmount",Matchers.equalTo(21314234));
    }

    @Test
    void updateBook(){
        mockBook.setId(1L);
        mockBook.setTitle("House of dragon");
        Mockito.when(bookService.update(book1)).thenReturn(mockBook);
        Mockito.when(authorService.findById(1L)).thenReturn(author1);
        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(bookRequestDto)
                .queryParam("id", 1L)
                .when()
                .put("/books")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("title", Matchers.equalTo("House of dragon"))
                .body("publishedAmount", Matchers.equalTo(2022123))
                .body("soldAmount", Matchers.equalTo(21314234));
    }

    @Test
    void showBestSellingBook(){
        Mockito.when(bookService.getBestSellingBook("H. P. Lovecraft")).thenReturn(book1);
        RestAssuredMockMvc
                .given().queryParam("by-author", "H. P. Lovecraft")
                .when().get("/books/bestseller")
                .then().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("title", Matchers.equalTo("Harry Potter"))
                .body("publishedAmount", Matchers.equalTo(2022123))
                .body("soldAmount", Matchers.equalTo(21314234));
    }

    @Test
    void showMostPublishedBook(){
        Mockito.when(bookService.getMostPublishedBook("H. P. Lovecraft")).thenReturn(book1);
        RestAssuredMockMvc
                .given().queryParam("by-author", "H. P. Lovecraft")
                .when().get("/books/most-published")
                .then().statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("title", Matchers.equalTo("Harry Potter"))
                .body("publishedAmount", Matchers.equalTo(2022123))
                .body("soldAmount", Matchers.equalTo(21314234));
    }

    @Test
    void showListOfBestSellingBooks(){
        List<Book> mockList = List.of(book1, book2, book3);
        Mockito.when(bookService.getBestSellingBooks("H. P. Lovecraft")).thenReturn(mockList);
        RestAssuredMockMvc
                .given().queryParam("by-author","H. P. Lovecraft")
                .when().get("/books/bestsellers")
                .then().statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].title", Matchers.equalTo("Harry Potter"))
                .body("[0].publishedAmount", Matchers.equalTo(2022123))
                .body("[0].soldAmount", Matchers.equalTo(21314234))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].title", Matchers.equalTo("House of dragon"))
                .body("[1].publishedAmount", Matchers.equalTo(1123124))
                .body("[1].soldAmount", Matchers.equalTo(23441232))
                .body("[2].id", Matchers.equalTo(3))
                .body("[2].title", Matchers.equalTo("Twilight"))
                .body("[2].publishedAmount", Matchers.equalTo(12313123))
                .body("[2].soldAmount", Matchers.equalTo(34234));
    }

    @Test
    void showListMostPublishedBooks(){
        List<Book> mockList = List.of(book1, book2, book3);
        Mockito.when(bookService.getMostPublishedBooks("H. P. Lovecraft")).thenReturn(mockList);
        RestAssuredMockMvc
                .given().queryParam("by-author","H. P. Lovecraft")
                .when().get("/books/list-most-published")
                .then().statusCode(200)
                .body("size()", Matchers.equalTo(3))
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].title", Matchers.equalTo("Harry Potter"))
                .body("[0].publishedAmount", Matchers.equalTo(2022123))
                .body("[0].soldAmount", Matchers.equalTo(21314234))
                .body("[1].id", Matchers.equalTo(2))
                .body("[1].title", Matchers.equalTo("House of dragon"))
                .body("[1].publishedAmount", Matchers.equalTo(1123124))
                .body("[1].soldAmount", Matchers.equalTo(23441232))
                .body("[2].id", Matchers.equalTo(3))
                .body("[2].title", Matchers.equalTo("Twilight"))
                .body("[2].publishedAmount", Matchers.equalTo(12313123))
                .body("[2].soldAmount", Matchers.equalTo(34234));
    }

    @Test
    void showMostSuccessfulBook(){
        List<Book> mockList = List.of(this.book3);
        Mockito.when(bookService.getMostSuccessfulBooks("H. P. Lovecraft")).thenReturn(mockList);
        RestAssuredMockMvc
                .given().queryParam("by-author","H. P. Lovecraft")
                .when().get("/books/successful")
                .then()
                .body("[0].id", Matchers.equalTo(3))
                .body("[0].title", Matchers.equalTo("Twilight"))
                .body("[0].publishedAmount", Matchers.equalTo(12313123))
                .body("[0].soldAmount", Matchers.equalTo(34234));
    }
}

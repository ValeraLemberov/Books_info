package com.mateacademy.books.info.repository;

import com.mateacademy.books.info.model.Author;
import com.mateacademy.books.info.model.Book;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void findAllBooksByAuthorName_ok(){
        List<Book> actual = bookRepository.findAllBooksByAuthorName("Stephen King");
        Assertions.assertEquals(4,actual.size());
        Assertions.assertEquals(3, actual.get(0).getAuthors().size());
        Assertions.assertEquals(3, actual.get(1).getAuthors().size());
        Assertions.assertEquals(4, actual.get(2).getAuthors().size());
        Assertions.assertEquals(2 ,actual.get(3).getAuthors().size());
        Assertions.assertEquals("Harry Potter" ,actual.get(0).getBookName());
        Assertions.assertEquals("Girl with the Dragon Tattoo,The:Millennium Trilogy", actual.get(1).getBookName());
        Assertions.assertEquals("New Moon", actual.get(2).getBookName());
        Assertions.assertEquals("Curious Incident of the Dog in the Night-time" ,actual.get(3).getBookName());
    }

    @Test
    void findAllBooksByAuthorName_nonExistentAuthor(){
        List<Book> actual = bookRepository.findAllBooksByAuthorName("Sten Lee");
        Assertions.assertEquals(0,actual.size());
    }

    @Test
    void findMostPublishedAmount_ok(){
        Book actual = bookRepository.findBestSoldAmount("John Grisham");
        Assertions.assertNotNull(actual);
        Assertions.assertEquals("Fifty Shades Freed", actual.getBookName());
        Assertions.assertEquals(5, actual.getAuthors().size());
        Assertions.assertEquals(24567545, actual.getPublishedAmount());
        Assertions.assertEquals(65736545, actual.getSoldAmount());
    }

    @Test
    void findMostPublishedAmount_nonExistentAuthor(){
        Book actual = bookRepository.findBestSoldAmount("Jeki Chan");
        Assertions.assertNull(actual);
    }

    @Test
    void findBestSoldAmount_ok(){
        Book actual = bookRepository.findBestSoldAmount("Lev Tolstoy");
        Assertions.assertNotNull(actual);
        Assertions.assertEquals("Fifty Shades Freed", actual.getBookName());
        Assertions.assertEquals(5, actual.getAuthors().size());
        Assertions.assertEquals(24567545, actual.getPublishedAmount());
        Assertions.assertEquals(65736545, actual.getSoldAmount());
    }

    @Test
    void findBestSoldAmount_nonExistentAuthor(){
        Book actual = bookRepository.findBestSoldAmount("Amanda Waller");
        Assertions.assertNull(actual);
    }

    @Test
    void findAllBySoldAmount_ok(){
        List<Book> actual = bookRepository.findAllBySoldAmount("ing");
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(1, actual.get(0).getAuthors().size());
        Assertions.assertEquals("Digital Fortress" , actual.get(0).getBookName());
        Assertions.assertEquals(45635653, actual.get(0).getSoldAmount());
    }

    @Test
    void findAllBySoldAmount_canNotFindMatch(){
        Book actual = bookRepository.findBestSoldAmount("24");
        Assertions.assertNull(actual);
    }

    @Test
    void findAllByPublishedAmount_ok(){
        List<Book> actual = bookRepository.findAllByPublishedAmount("ing");
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("Girl with the Dragon Tattoo,The:Millennium Trilogy",
                actual.get(0).getBookName());
        Assertions.assertEquals(3, actual.get(0).getAuthors().size());
        Assertions.assertEquals(12312334, actual.get(0).getPublishedAmount());
        Assertions.assertEquals(5564567, actual.get(0).getSoldAmount());
    }

    @Test
    void findAllByPublishedAmount_canNotFindMatch(){
        List<Book> actual = bookRepository.findAllByPublishedAmount("23");
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void findMostSuccessfulBooks_ok(){
        List<Book> actual = bookRepository.findMostSuccessfulBooks("John");
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals(3, actual.get(0).getAuthors().size());
        Assertions.assertEquals("House of dragon",actual.get(0).getBookName());
        Assertions.assertEquals(1123124 ,actual.get(0).getPublishedAmount());
        Assertions.assertEquals(23441232 ,actual.get(0).getSoldAmount());
    }

    @Test
    void findMostSuccessfulBooks_canNotFindMatch(){
        List<Book> actual = bookRepository.findMostSuccessfulBooks("12");
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void findMostSuccessfulAuthor_ok(){
        Author actual = bookRepository.findMostSuccessfulAuthor();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals("J. K. Rowling", actual.getAuthorName());
        Assertions.assertEquals("1965-07-31", actual.getBirthDate().toString());
        Assertions.assertEquals("5384855043", actual.getPhone());
        Assertions.assertEquals("J.R.@gmail.com", actual.getEmail());
    }
}
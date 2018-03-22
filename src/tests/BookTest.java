package tests;

import pl.books.Book;
import pl.books.BookGenres;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void shouldSetTodayDate() {
        Book book = new Book("tytuł", "autor", BookGenres.ACTION);
        book.setDateOfLoan();
        LocalDate date = book.getDateOfLoan();
        assertTrue(date.equals(LocalDate.now()));
    }

    @Test
    void shouldSayItIsEqual() {
        Book book = new Book("tytuł", "autor", BookGenres.ACTION);
        Book book1 = new Book("tytuł", "autor", BookGenres.ACTION);
        assertTrue(book.equals(book1));
        assertTrue(book.equals(book));
    }
}
package pl.books;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Book implements Serializable{
    private String title;   //tytul
    private String author;  //autor
    private BookGenres genre;   //rodzaj
    private LocalDate dateOfLoan;   //data wypozyczenia

    public Book(String title, String author, BookGenres genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public void setDateOfLoan () {
        dateOfLoan = LocalDate.now();
    } //ustawia dzien wypozyczenia na dzisiejszy

    public LocalDate getDateOfLoan() {
        return dateOfLoan;
    }  //zwraca date wypozyczenia

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title.equals(book.title) &&
                author.equals(book.author) &&
                genre == book.genre;
    }

    @Override
    public int hashCode() {
        return 23*Objects.hash(title, author, genre);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", Genre: " + genre;
    }
}


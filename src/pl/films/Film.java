package pl.films;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Film implements Serializable{
    private String title;       //tytul
    private FilmGenre genre;    //rodzaj
    private LocalDate dateOfLoan;   //data wypozyczenia

    public Film(String title, FilmGenre genre) {
        this.title = title;
        this.genre = genre;
    }

    public LocalDate getDateOfLoan() {
        return dateOfLoan;
    } //pobiera date wypozyczenia

    public void setDateOfLoan() {
        dateOfLoan = LocalDate.now();
    }   //ustaiwa date wypozyczenia na dzien dzisiejszy

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return title.equals(film.title) &&
                genre == film.genre;
    }

    @Override
    public int hashCode() {
        return 7*Objects.hash(title, genre);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Genre: " + genre;
    }
}

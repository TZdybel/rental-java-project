package tests;

import pl.films.Film;
import pl.films.FilmGenre;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {

    @Test
    void shouldSetTodayDate() {
        Film film = new Film("tytuł", FilmGenre.ADVENTURE);
        film.setDateOfLoan();
        LocalDate date = film.getDateOfLoan();
        assertTrue(date.equals(LocalDate.now()));
    }
}
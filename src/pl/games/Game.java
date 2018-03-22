package pl.games;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Game implements Serializable{
    private String title;           //tytul
    private GameGenre genre;        //rodzaj
    private LocalDate dateOfLoan;   //data wypozyczenia

    public Game(String title, GameGenre genre) {
        this.title = title;
        this.genre = genre;
    }

    public LocalDate getDateOfLoan() {
        return dateOfLoan;
    }   //pobiera date wypozyczenia

    public void setDateOfLoan() {
        dateOfLoan = LocalDate.now();
    }   //ustawia date wypozyczenia na dzien dzisiejszy

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return title.equals(game.title) &&
                genre == game.genre;
    }

    @Override
    public int hashCode() {
        return 13*Objects.hash(title, genre);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Genre: " + genre;
    }
}

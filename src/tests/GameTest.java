package tests;

import pl.games.Game;
import pl.games.GameGenre;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void setDateOfLoan() {
        Game game = new Game("tytul", GameGenre.ACTION);
        game.setDateOfLoan();
        LocalDate date = game.getDateOfLoan();
        assertTrue(date.equals(LocalDate.now()));
    }
}
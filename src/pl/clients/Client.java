package pl.clients;

import pl.books.Book;
import pl.books.SetOfBooks;
import pl.films.Film;
import pl.films.SetOfFilms;
import pl.games.Game;
import pl.games.SetOfGames;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Client implements Serializable{
    private String firstName;       //imie
    private String lastName;        //nazwisko
    private String address;         //adres
    private String PESEL;           //PESEl
    private int yearOfBirth;        //rok urodzenia
    private long clientID;          //identyfikator klienta podawany przy zakladaniu "konta"

    private List<Book> loanedBooks = new LinkedList<>();       //zbior wypozyczonych ksiazek
    private List<Film> loanedFilms = new LinkedList<>();       //zbior wypozyczonych filmow
    private List<Game> loanedGames = new LinkedList<>();       //zbior wypozyczonych gier

    public Client(String firstName, String lastName, String address, String PESEL, int yearOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.PESEL = PESEL;
        this.yearOfBirth = yearOfBirth;
    }

    public long getClientID() {
        return clientID;
    }      //zwraca identyfikator klienta

    public void setClientID(long x) {
        if (clientID == 0) clientID = x;
        else System.out.println("Nie można zmienić przydzielonego clientID");
    }       //ustawia identyfikator klienta (jednorazowo)

    public void loanBook(Book book, SetOfBooks setOfBooks) {
        int tmp = setOfBooks.removeBook(book);
        if (tmp == 1) {
            book.setDateOfLoan();
            loanedBooks.add(book);
            System.out.println("Book loaned");
        } else if (tmp == 0) {
            System.out.println("No available copies");
        } else {
            System.out.println("No such book");
        }
    }   //wypozyczenie ksiazki

    public void loanFilm(Film film, SetOfFilms setOfFilms) {
        int tmp = setOfFilms.removeFilm(film);
        if (tmp == 1) {
            film.setDateOfLoan();
            loanedFilms.add(film);
            System.out.println("Film loaned");
        } else if (tmp == 0) {
            System.out.println("No available copies");
        } else {
            System.out.println("No such film");
        }
    }   //wypozyczenie filmu

    public void loanGame(Game game, SetOfGames setOfGames) {
        int tmp = setOfGames.removeGame(game);
        if (tmp == 1) {
            game.setDateOfLoan();
            loanedGames.add(game);
            System.out.println("Game loaned");
        } else if (tmp == 0) {
            System.out.println("No available copies");
        } else {
            System.out.println("No such game");
        }
    }   //wypozyczenie gry

    private double countFee(LocalDate date) {
        LocalDate today = LocalDate.of(2018, 2, 3);
        int diff;
        if (today.getYear() != date.getYear()) {
            diff = today.getDayOfYear() + date.lengthOfYear() - date.getDayOfYear();
        }
        else {
            diff = today.getDayOfYear() - date.getDayOfYear();
        }
        if (diff > 14) return (diff-14)*0.2;          //kara za przetrzymanie rzeczy wynosi 0.2zł za dzień
        return 0;
    }       //funkcja obliczajaca kare za spoznione oddanie wypozyczenia

    public double returnBook(Book book, SetOfBooks setOfBooks) {
        if (loanedBooks.contains(book)) {
            int tmp = loanedBooks.indexOf(book);
            Book realBook = loanedBooks.get(tmp);
            loanedBooks.remove(realBook);
            setOfBooks.addBook(book);
            System.out.println("Return successful!");
            return countFee(realBook.getDateOfLoan());
        }
        System.out.println("No such book on loan");
        return -1;
    }   //zwrot ksiazki - zwraca wartosc ewentualnej kary

    public double returnFilm(Film film, SetOfFilms setOfFilms) {
        if (loanedFilms.contains(film)) {
            int tmp = loanedFilms.indexOf(film);
            Film realFilm = loanedFilms.get(tmp);
            loanedFilms.remove(realFilm);
            setOfFilms.addFilm(film);
            System.out.println("Return successful!");
            return countFee(realFilm.getDateOfLoan());
        }
        System.out.println("No such film on loan");
        return -1;
    }   //zwrot filmu - zwraca wartosc ewentualnej kary

    public double returnGame(Game game, SetOfGames setOfGames) {
        if (loanedGames.contains(game)) {
            int tmp = loanedGames.indexOf(game);
            Game realGame = loanedGames.get(tmp);
            loanedGames.remove(realGame);
            setOfGames.addGame(game);
            System.out.println("Return successful!");
            return countFee(realGame.getDateOfLoan());
        }
        System.out.println("No such game on loan");
        return -1;
    }   //zwrot game - zwraca wartosc ewentualnej kary

    public void checkBookLoans() {
        System.out.println("Loaned books: ");
        Iterator<Book> it = loanedBooks.iterator();
        int tmp = 1;
        while (it.hasNext()) {
            System.out.println(tmp + ". " + it.next());
            tmp++;
        }
        System.out.println("-----------------------------------------------");
    }       //wypisanie wypozyczen ksiazek na ekran

    public void checkFilmLoans() {
        System.out.println("Loaned films: ");
        Iterator<Film> it = loanedFilms.iterator();
        int tmp = 1;
        while (it.hasNext()) {
            System.out.println(tmp + ". " + it.next());
            tmp++;
        }
        System.out.println("-----------------------------------------------");
    }       //wypisanie wypozyczen filmow na ekran

    public void checkGameLoans() {
        System.out.println("Loaned games: ");
        Iterator<Game> it = loanedGames.iterator();
        int tmp = 1;
        while (it.hasNext()) {
            System.out.println(tmp + ". " + it.next());
            tmp++;
        }
        System.out.println("-----------------------------------------------");
    }       //wypisanie wypozyczen gier na ekran

    public boolean hasNoLoans() {
        return loanedBooks.isEmpty() && loanedGames.isEmpty() && loanedFilms.isEmpty();
    }       //zwraca true jesli klient nie ma zadnych aktywnych wypozyczen

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientID == client.clientID;
    }       //porownywanie wylacznie za pomoca identyfikatora, ktory jest unikalny dla kazdego klienta

    @Override
    public int hashCode() {
        return 29*Objects.hash(clientID);
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + ", Last Name: " + lastName + ", Address: " + address + ", PESEL: " + PESEL + ", Birth Year: " + yearOfBirth + " -- ID: " + clientID;
    }
}

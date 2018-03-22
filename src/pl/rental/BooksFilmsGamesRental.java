package pl.rental;

import pl.books.Book;
import pl.books.BookGenres;
import pl.books.SetOfBooks;
import pl.clients.Client;
import pl.films.Film;
import pl.films.FilmGenre;
import pl.films.SetOfFilms;
import pl.games.Game;
import pl.games.GameGenre;
import pl.games.SetOfGames;

import java.io.Serializable;
import java.util.*;

public class BooksFilmsGamesRental implements Serializable{
    private SetOfGames setOfGames = new SetOfGames();
    private SetOfFilms setOfFilms = new SetOfFilms();
    private SetOfBooks setOfBooks = new SetOfBooks();
    private Set<Client> clients = new HashSet<>();
    private long actualFreeClientID = 1;

    public long getActualFreeClientID() {
        return actualFreeClientID;
    }       //zwraca identyfikator klienta, ktry zostanie przydzielony nast//wypisanie wypozyczen ksiazek na ekrannemu dodanemu

    public boolean addClient() {
        Client client = createClient();
        try {
            client.setClientID(actualFreeClientID++);
        }
        catch (NullPointerException e) {
            actualFreeClientID--;
            return false;
        }
        return clients.add(client);
    }       //dodaje klienta do bazy danych, jesli sie udalo zwraca true, jesli nie - false

    public boolean removeClient(long clientID) {
        Iterator<Client> it = clients.iterator();
        while(it.hasNext()) {
            Client c = it.next();
            if (c.getClientID() == clientID) {
                if (c.hasNoLoans()) return clients.remove(c);
                else {
                    System.out.println("Client has not returned all loans");
                }
            }
        }
        return false;
    }   //usuwa klienta z bazy danych jesli nie ma on zadnych aktywnych wypozyczen, jesli sie udalo zwraca true, jesli nie - false

    public Client findClient(long clientID) {
        Iterator<Client> it = clients.iterator();
        while(it.hasNext()) {
            Client c = it.next();
            if (c.getClientID() == clientID) {
                return c;
            }
        }
        return null;
    } //przeszukuje zbior klientow w poszukiwaniu jednego konkretnego, jesli go znaleziono - zwraca go, jesli nie - zwraca null

    public void showAllClients() {
        Iterator<Client> it = clients.iterator();
        int tmp = 1;
        while (it.hasNext()) {
            System.out.println("--" + tmp + ".   " + it.next());
            tmp++;
        }
        System.out.println("");
    }  //wypisuje zbior klientow na ekran

    public void showAllSets() {
        System.out.println(setOfBooks);
        System.out.println(setOfFilms);
        System.out.println(setOfGames);
    }   //wypisuje wszystkie zbiory (ksiazek, filmow, gier) na ekran, oddzielone od siebie

    public void fillSetOfBooks() {
        Scanner scanner = new Scanner(System.in);
        String next = "Y";
        while (next.equals("y") || next.equals("Y")) {
            Book book = createBook();
            System.out.print("Amount - ");
            Integer amount = null;
            try {
                amount = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Amount needs to be a number");
            }
            if (amount != null && amount > 0) setOfBooks.addBook(book, amount);
            else if (amount != null && amount <= 0) System.out.println("Amount needs to be a positive number");
            scanner.nextLine();
            System.out.println("Add next? (Y/y)");
            next = scanner.nextLine();
        }
    }   //wypelnia zbior ksiazek wprowadzonymi danymi dopoki uzytkownik bedzie chcial

    public Book createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Title - ");
        String title = scanner.nextLine();
        System.out.print("Author - ");
        String author = scanner.nextLine();
        System.out.println("Available genres: Fantasy, SFI, Action, Horror, Drama, Adventure, Crime, Poetry, Biography. Any other genre entered is taken as \"Other\".");
        System.out.print("Genre - ");
        String genre = scanner.nextLine();
        BookGenres bookGenre;
        switch (genre) {
            case "Fantasy":
                bookGenre = BookGenres.FANTASY;
                break;
            case "SFI":
                bookGenre = BookGenres.SFI;
                break;
            case "Action":
                bookGenre = BookGenres.ACTION;
                break;
            case "Horror":
                bookGenre = BookGenres.HORROR;
                break;
            case "Drama":
                bookGenre = BookGenres.DRAMA;
                break;
            case "Adventure":
                bookGenre = BookGenres.ADVENTURE;
                break;
            case "Crime":
                bookGenre = BookGenres.CRIME;
                break;
            case "Poetry":
                bookGenre = BookGenres.POETRY;
                break;
            case "Biography":
                bookGenre = BookGenres.BIOGRAPHY;
                break;
            default:
                bookGenre = BookGenres.OTHER;
                break;
        }
        return new Book(title, author, bookGenre);
    }   //tworzy obiekt ksiazki - w celu np. jej wstawienia do zbioru czy szukania w tymze zbiorze

    public void fillSetOfFilms() {
        Scanner scanner = new Scanner(System.in);
        String next = "Y";
        while (next.equals("y") || next.equals("Y")) {
            Film film = createFilm();
            System.out.print("Amount - ");
            Integer amount = null;
            try {
                amount = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Amount needs to be a number");
            }
            if (amount != null && amount > 0) setOfFilms.addFilm(film, amount);
            else if (amount != null && amount <= 0) System.out.println("Amount needs to be a positive number");
            scanner.nextLine();
            System.out.println("Add next? (Y/y)");
            next = scanner.nextLine();
        }
    }   //wypelnia zbior filmow wprowadzonymi danymi dopoki uzytkownik bedzie chcial

    public Film createFilm() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Title - ");
        String title = scanner.nextLine();
        System.out.println("Available genres: Comedy, Drama, Western, Horror, Thriller, Crime, SFI, Fantasy, Animated, Historical, Adventure. Any other genre entered is taken as \"Other\".");
        System.out.print("Genre - ");
        String genre = scanner.nextLine();
        FilmGenre filmGenre;
        switch (genre) {
            case "Comedy":
                filmGenre = FilmGenre.COMEDY;
                break;
            case "Drama":
                filmGenre = FilmGenre.DRAMA;
                break;
            case "Western":
                filmGenre = FilmGenre.WESTERN;
                break;
            case "Horror":
                filmGenre = FilmGenre.HORROR;
                break;
            case "Thriller":
                filmGenre = FilmGenre.THRILLER;
                break;
            case "Crime":
                filmGenre = FilmGenre.CRIME;
                break;
            case "SFI":
                filmGenre = FilmGenre.SFI;
                break;
            case "Fantasy":
                filmGenre = FilmGenre.FANTASY;
                break;
            case "Animated":
                filmGenre = FilmGenre.ANIMATED;
                break;
            case "Historical":
                filmGenre = FilmGenre.HISTORICAL;
                break;
            case "Adventure":
                filmGenre = FilmGenre.ADVENTURE;
                break;
            default:
                filmGenre = FilmGenre.OTHER;
                break;
        }
        return new Film(title, filmGenre);
    }   //tworzy obiekt filmu - w celu np. jej wstawienia do zbioru czy szukania w tymze zbiorze

    public void fillSetOfGames() {
        Scanner scanner = new Scanner(System.in);
        String next = "Y";
        while (next.equals("y") || next.equals("Y")) {
            Game game = createGame();
            System.out.print("Amount - ");
            Integer amount = null;
            try {
                amount = scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Amount needs to be a number");
            }
            if (amount != null && amount > 0) setOfGames.addGame(game, amount);
            else if (amount != null && amount <= 0) System.out.println("Amount needs to be a positive number");
            scanner.nextLine();
            System.out.println("Add next? (Y/y)");
            next = scanner.nextLine();
        }
    }   //wypelnia zbior gier wprowadzonymi danymi dopoki uzytkownik bedzie chcial

    public Game createGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Title - ");
        String title = scanner.nextLine();
        System.out.println("Available genres: Action, RPG, FPS, TPS, Strategy, Racing, Sports, MMO, Adventure. Any other genre entered is taken as \"Other\".");
        System.out.print("Genre - ");
        String genre = scanner.nextLine();
        GameGenre gameGenre;
        switch (genre) {
            case "Action":
                gameGenre = GameGenre.ACTION;
                break;
            case "RPG":
                gameGenre = GameGenre.RPG;
                break;
            case "FPS":
                gameGenre = GameGenre.FPS;
                break;
            case "TPS":
                gameGenre = GameGenre.TPS;
                break;
            case "Strategy":
                gameGenre = GameGenre.STRATEGY;
                break;
            case "Racing":
                gameGenre = GameGenre.RACING;
                break;
            case "Sports":
                gameGenre = GameGenre.SPORTS;
                break;
            case "MMO":
                gameGenre = GameGenre.MMO;
                break;
            case "Adventure":
                gameGenre = GameGenre.ADVENTURE;
                break;
            default:
                gameGenre = GameGenre.OTHER;
                break;
        }
        return new Game(title, gameGenre);
    }   //tworzy obiekt gry - w celu np. jej wstawienia do zbioru czy szukania w tymze zbiorze

    public void addLoan(long clientID, Book book) {
        Client client = findClient(clientID);
        try {
            client.loanBook(book, setOfBooks);
        }
        catch (NullPointerException e) {
            System.out.println("This client does not exist");
        }
    }   //dodanie wypozyczenia ksiazki klientowi o podanym identyfikatorze

    public void addLoan(long clientID, Film film) {
        Client client = findClient(clientID);
        try {
            client.loanFilm(film, setOfFilms);
        }
        catch (NullPointerException e) {
            System.out.println("This client does not exist");
        }
    }   //dodanie wypozyczenia filmu klientowi o podanym identyfikatorze

    public void addLoan(long clientID, Game game) {
        Client client = findClient(clientID);
        try {
            client.loanGame(game, setOfGames);
        }
        catch (NullPointerException e) {
            System.out.println("This client does not exist");
        }
    }   //dodanie wypozyczenia gry klientowi o podanym identyfikatorze

    public double returnLoan(long clientID, Book book) {
        Client client = findClient(clientID);
        double fee;
        try {
            fee = client.returnBook(book, setOfBooks);
        }
        catch (NullPointerException e) {
            System.out.println("Client with this ID does not exist");
            return -1;
        }
        return fee;
    }   //zwrocenie przez klienta, o podanym identyfikatorze, ksiazki, zwraca ewentualna wartosc kary za przetrzymanie

    public double returnLoan(long clientID, Film film) {
        Client client = findClient(clientID);
        double fee;
        try {
            fee = client.returnFilm(film, setOfFilms);
        }
        catch (NullPointerException e) {
            System.out.println("Client with this ID does not exist");
            return -1;
        }
        return fee;
    }   //zwrocenie przez klienta, o podanym identyfikatorze, filmu, zwraca ewentualna wartosc kary za przetrzymanie

    public double returnLoan(long clientID, Game game) {
        Client client = findClient(clientID);
        double fee;
        try {
            fee = client.returnGame(game, setOfGames);
        }
        catch (NullPointerException e) {
            System.out.println("Client with this ID does not exist");
            return -1;
        }
        return fee;
    }   //zwrocenie przez klienta, o podanym identyfikatorze, gry, zwraca ewentualna wartosc kary za przetrzymanie

    public void checkLoans(long clientID) {
        Client client = findClient(clientID);
        client.checkBookLoans();
        client.checkFilmLoans();
        client.checkGameLoans();
    }      //wyswietla na ekran wszystkie wypozyczenia klienta o podanym identyfikatorze

    private Client createClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("First Name - ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name - ");
        String lastName = scanner.nextLine();
        System.out.print("Address - ");
        String address = scanner.nextLine();
        System.out.print("PESEL - ");
        String PESEL = scanner.nextLine();
        System.out.print("Year of birth - ");
        int yearOfBirth;
        try {
            yearOfBirth = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid data");
            return null;
        }
        return new Client(firstName, lastName, address, PESEL, yearOfBirth);
    }      //tworzy instancje klienta i ja zwraca

    public boolean isAvailable(Book book) {
        Integer value = setOfBooks.contains(book);
        System.out.println("");
        if (value > 0) {
            System.out.println("Available copies: " + value);
            System.out.println("");
            return true;
        }
        else if (value == 0) {
            System.out.println("Available copies: " + 0);
            System.out.println("");
            return false;
        }
        else {
            System.out.println("No such book in set");
            System.out.println("");
            return false;
        }
    }   //zwraca true jesli podana ksiazka jest dostepna do wypozyczenia, jesli nie - false

    public boolean isAvailable(Film film) {
        Integer value = setOfFilms.contains(film);
        if (value > 0) {
            System.out.println("Available copies: " + value);
            return true;
        }
        else if (value == 0) {
            System.out.println("Available copies: " + 0);
            return false;
        }
        else {
            System.out.println("No such book in set");
            return false;
        }
    }   //zwraca true jesli podany film jest dostepny do wypozyczenia, jesli nie - false

    public boolean isAvailable(Game game) {
        Integer value = setOfGames.contains(game);
        if (value > 0) {
            System.out.println("Available copies: " + value);
            return true;
        }
        else if (value == 0) {
            System.out.println("Available copies: " + 0);
            return false;
        }
        else {
            System.out.println("No such book in set");
            return false;
        }
    }   //zwraca true jesli podana gra jest dostepna do wypozyczenia, jesli nie - false
}

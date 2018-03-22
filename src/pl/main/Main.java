package pl.main;

import pl.rental.BooksFilmsGamesRental;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BooksFilmsGamesRental rental = new BooksFilmsGamesRental();
        Scanner scanner = new Scanner(System.in);

        //Wczytanie stanu z pliku

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("rental.bin"))) {
            rental = (BooksFilmsGamesRental) inputStream.readObject();
        } catch (IOException e) {
            System.out.println("Nie ma pliku do wczytania");;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //--------------------------------------

        boolean program = true;     //zmienna podtrzymujaca dzialanie programu, jesli ustawiona na false - program konczy dzialanie

        while(program) {

            //opcje do wyboru

            System.out.println("What you want to do?");
            System.out.println("1 - Add new client");
            System.out.println("2 - Check if book/film/game is available");
            System.out.println("3 - Loan book/film/game");
            System.out.println("4 - Receive a return");
            System.out.println("5 - Show client's loans");
            System.out.println("6 - Show every client");
            System.out.println("7 - Show all rental's sets");
            System.out.println("8 - Add new things to rental's sets");
            System.out.println("9 - Remove a client from database");
            System.out.println("0 - End program");

            //----------------------------

            String choice = scanner.nextLine();     //zmienna przechowujaca jaki wybor dokonano
            switch (choice) {
                case "1":                                                                                       //1.dodanie klienta
                    System.out.println("Enter client details");
                    if (rental.addClient()) {
                        System.out.println("Client added!");
                        System.out.println("ID of this client is: " + (rental.getActualFreeClientID() - 1));    //wypisuje przypisany identyfikator
                    }
                    else System.out.println("Cannot add this client");
                    break;
                case "2":                                                                                       //2.sprawdzenie czy dany przedmiot jest na stanie
                    System.out.println("What are you looking for?");
                    System.out.println("1 - Book");
                    System.out.println("2 - Film");
                    System.out.println("3 - Game");
                    String tmp = scanner.nextLine();    //zmienna wyboru
                    switch(tmp) {
                        case "1":
                            System.out.println("Enter book details:");
                            rental.isAvailable(rental.createBook());
                            break;
                        case "2":
                            System.out.println("Enter film details:");
                            rental.isAvailable(rental.createFilm());
                            break;
                        case "3":
                            System.out.println("Enter game details:");
                            rental.isAvailable(rental.createGame());
                            break;
                        default:
                            System.out.println("No such option");
                            break;
                    }
                    break;
                case "3":                                                                                       //3.Wypozyczenie przedmiotu
                    int clientID = Main.getClientID(rental);
                    if (clientID == 0) break;
                    System.out.println("Specify loan type: ");
                    System.out.println("1 - Book");
                    System.out.println("2 - Film");
                    System.out.println("3 - Game");
                    tmp = scanner.nextLine();   //zmienna wyboru
                    switch(tmp) {
                        case "1":
                            System.out.println("Enter book details:");
                            rental.addLoan(clientID, rental.createBook());
                            break;
                        case "2":
                            System.out.println("Enter film details:");
                            rental.addLoan(clientID, rental.createFilm());
                            break;
                        case "3":
                            System.out.println("Enter game details:");
                            rental.addLoan(clientID, rental.createGame());
                            break;
                        default:
                            System.out.println("No such option");
                            break;
                    }
                    break;
                case "4":                                                                                      //4.Przyjmowanie zwrotow wypozyczen
                    clientID = Main.getClientID(rental);
                    if (clientID == 0) break;
                    System.out.println("Return of what you want to execute?");
                    System.out.println("1 - Book");
                    System.out.println("2 - Film");
                    System.out.println("3 - Game");
                    tmp = scanner.nextLine();      //zmienna wyboru
                    double fee = 0;     //kara za przetrzymanie
                    switch(tmp) {
                        case "1":
                            System.out.println("Enter book details:");
                            fee = rental.returnLoan(clientID, rental.createBook());
                            break;
                        case "2":
                            System.out.println("Enter film details:");
                            fee = rental.returnLoan(clientID, rental.createFilm());
                            break;
                        case "3":
                            System.out.println("Enter game details:");
                            fee = rental.returnLoan(clientID, rental.createGame());
                            break;
                        default:
                            System.out.println("No such option");
                            break;
                    }
                    if (fee != 0 && fee != -1) {
                        System.out.print("Fee = ");
                        System.out.format("%.2f%n", fee);
                    }
                    break;
                case "5":                                                                                      //5.wypisanie wszystkich wypozyczen klienta
                    clientID = Main.getClientID(rental);
                    if (clientID == 0) break;
                    rental.checkLoans(clientID);
                    break;
                case "6":                                                                                      //6.wypisanie na ekran zbioru klientow
                    rental.showAllClients();
                    break;
                case "7":                                                                                      //7.Wypisanie na ekran kompletnych zbiorow przedmiotow
                    rental.showAllSets();
                    break;
                case "8":                                                                                      //8.dodawanie nowych przedmiotów do zbioru wypożyczalni
                    System.out.println("What set you want to update?");
                    System.out.println("1 - Books");
                    System.out.println("2 - Films");
                    System.out.println("3 - Games");
                    tmp = scanner.nextLine();      //zmienna wyboru
                    switch(tmp) {
                        case "1":
                            rental.fillSetOfBooks();
                            break;
                        case "2":
                            rental.fillSetOfFilms();
                            break;
                        case "3":
                            rental.fillSetOfGames();
                            break;
                        default:
                            System.out.println("No such option");
                            break;
                    }
                    break;
                case "9":                                                                                      //9.usuniecie klienta z bazy, jesli nie ma zadnych wypozyczen aktywnych
                    clientID = Main.getClientID(rental);
                    if (clientID == 0) break;
                    if (rental.removeClient(clientID)) System.out.println("Client removed");
                    else System.out.println("Client cannot be removed, check input data");
                    break;
                case "0":                                                                                      //0.zakonczenie dzialania programu
                    try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("rental.bin"))) {
                        outputStream.writeObject(rental);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    program = false;
                    break;
                default:                                                                                       //w przypadku gdy uzytkownik wprowadzil inna wartosc niz spodziewane
                    System.out.println("This option does not exist");
                    break;
            }
        }
    }

    private static int getClientID(BooksFilmsGamesRental rental) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ClientID");
        int clientID;
        try {
            clientID = scanner.nextInt();
        }
        catch (InputMismatchException e) {
            System.out.println("ClientID should be an integer");
            return 0;
        }
        if (rental.findClient(clientID) == null) {
            System.out.println("Client does not exist");
            return 0;
        }
        else return clientID;
    }       //metoda statyczna, sluzaca do pobierania identyfikatora klienta i jego zwracania,
                                                                             // z jednoczesnym sprawdzaniem czy taki klient istnieje, jesli nie zwraca 0 - kod bledu
}

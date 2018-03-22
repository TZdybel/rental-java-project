package pl.books;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SetOfBooks implements Serializable{
    private Map<Book, Integer> allBooks = new HashMap<>();  //mapa zawierajaca unikalne ksiazki jako klucz i int jako ich ilosc na stanie

    public void addBook(Book book) {
        if (allBooks.containsKey(book)) {
            Integer tmp = allBooks.get(book);
            tmp += 1;
            allBooks.put(book, tmp);
        } else {
            allBooks.put(book, 1);
        }
    }           //dodanie jednej ksiazki do zbioru

    public void addBook(Book book, Integer quantity) {
        if (allBooks.containsKey(book)) {
            Integer tmp = allBooks.get(book);
            tmp += quantity;
            allBooks.put(book, tmp);
        } else {
            allBooks.put(book, quantity);
        }
    }   //dodanie podanej ilosci kopii ksiazki do zbioru

    public int removeBook(Book book) {
        if (allBooks.containsKey(book)) {
            if (allBooks.get(book) > 0) {
                Integer tmp = allBooks.get(book);
                tmp -= 1;
                allBooks.put(book, tmp);
                return 1;
            }
            return 0;
        }
        return -1;
    }       //usuniecie pojedynczej ksiazki ze zbioru, zwraca 1 jesli sie udalo, 0 jesli nie ma jej na stanie, -1 jesli nie ma takiej w zbiorze

    public Integer contains(Book book) {
        if (allBooks.containsKey(book)) {
            Integer tmp = allBooks.get(book);
            if (tmp > 0) return tmp;
            else return 0;
        }
        else return -1;
    }       //sprawdza, czy podana ksiazka zawiera sie w zbiorze, jesli tak - zwraca liczbe jej kopii, jesli nie - zwraca -1

    @Override
    public String toString() {
        System.out.println("Set of books: ");
        int tmp = 1; //licznik
        for (Map.Entry<Book, Integer> entry : allBooks.entrySet()) {
            Book book = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println(tmp + ".  " + book + " -- " + quantity + " copy/copies");
            tmp++;
        }
        System.out.println("------------------------------------------------"); //oddzielenie od reszty
        return "";
    }
}

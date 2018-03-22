package pl.films;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SetOfFilms implements Serializable{
    private Map<Film, Integer> allFilms = new HashMap<>();  //mapa wszystkich filmow jako kluczy, wartoscia jest ich ilosc na stanie

    public void addFilm(Film film) {
        if (allFilms.containsKey(film)) {
            Integer tmp = allFilms.get(film);
            tmp += 1;
            allFilms.put(film, tmp);
        } else {
            allFilms.put(film, 1);
        }
    }  //dodawanie pojedynczego filmu do zbioru

    public void addFilm(Film film, Integer quantity) {
        if (allFilms.containsKey(film)) {
            Integer tmp = allFilms.get(film);
            tmp += quantity;
            allFilms.put(film, tmp);
        } else {
            allFilms.put(film, quantity);
        }
    }   //dodawanie kilku sztuk filmu do zbioru

    public int removeFilm(Film film) {
        if (allFilms.containsKey(film)) {
            if (allFilms.get(film) > 0) {
                Integer tmp = allFilms.get(film);
                tmp -= 1;
                allFilms.put(film, tmp);
                return 1;
            }
            return 0;
        }
        return -1;
    }   //usuwanie filmu ze zbioru, zwraca 1 jesli sie powiodlo, 0 jesli nie ma filmu na stanie, -1 jesli nie istnieje

    public Integer contains(Film film) {
        if (allFilms.containsKey(film)) {
            Integer tmp = allFilms.get(film);
            if (tmp > 0) return tmp;
            else return 0;
        }
        else return -1;
    }   //sprawdza czy podany film jest w zbiorze, jesli tak - zwraca jego ilosc kopii, jesli nie - -1

    @Override
    public String toString() {
        System.out.println("Set of films: ");
        int tmp = 1;    //licznik
        for (Map.Entry<Film, Integer> entry : allFilms.entrySet()) {
            Film film = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println(tmp + ".  " + film + " -- " + quantity + " copy/copies");
            tmp++;
        }
        System.out.println("------------------------------------------------"); //oddzielenie od reszty
        return "";
    }
}

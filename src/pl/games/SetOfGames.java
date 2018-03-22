package pl.games;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SetOfGames implements Serializable{
    private Map<Game, Integer> allGames = new HashMap<>();  //mapa wszystkich gier jako kluczy, jako wartosc - ich ilosc na stanie

    public void addGame(Game game) {
        if (allGames.containsKey(game)) {
            Integer tmp = allGames.get(game);
            tmp += 1;
            allGames.put(game, tmp);
        } else {
            allGames.put(game, 1);
        }
    }   //dodawanie pojedynczej gry do zbioru

    public void addGame(Game game, Integer quantity) {
        if (allGames.containsKey(game)) {
            Integer tmp = allGames.get(game);
            tmp += quantity;
            allGames.put(game, tmp);
        } else {
            allGames.put(game, quantity);
        }
    }   //dodawanie wiekszej ilosci kopii gry do zbioru

    public int removeGame(Game game) {
        if (allGames.containsKey(game)) {
            if (allGames.get(game) > 0) {
                Integer tmp = allGames.get(game);
                tmp -= 1;
                allGames.put(game, tmp);
                return 1;
            }
            return 0;
        }
        return -1;
    }   //usuwanie gry ze zbioru, zwraca 1 jesli udane, 0 jesli nie ma gry na stanie, -1 jesli gra nie istnieje

    public Integer contains(Game game) {
        if (allGames.containsKey(game)) {
            Integer tmp = allGames.get(game);
            if (tmp > 0) return tmp;
            else return 0;
        }
        else return -1;
    }   //sprawdza czy gra istnieje w zbiorze, jesli tak - zwraca ilosc kopii dostepnych, jesli nie - -1

    @Override
    public String toString() {
        System.out.println("Set of games: ");
        int tmp = 1; //licznik
        for (Map.Entry<Game, Integer> entry : allGames.entrySet()) {
            Game game = entry.getKey();
            Integer quantity = entry.getValue();
            System.out.println(tmp + ".  " + game + " -- " + quantity + " copy/copies");
            tmp++;
        }
        System.out.println("------------------------------------------------"); //oddzielenie od reszty
        return "";
    }

}

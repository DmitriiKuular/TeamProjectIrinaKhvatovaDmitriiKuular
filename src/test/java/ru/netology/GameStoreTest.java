package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test   //тест позволяет добавить игру в каталог - не находит в каталоге добавленной игры
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test   //тест на нахождение общего времени игры для одного игрока - не суммирует время
    public void shouldAddPlayTime() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        store.addPlayTime("Katya", 2);
        store.addPlayTime("Kolya", 3);
        store.addPlayTime("Katya", 4);
        store.addPlayTime("Petya", 5);

        String expected = "Katya";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test  //тест на поиск лучшего игрока из 3х разных игроков - тест проходит
    public void shouldGetMostPlayer() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        store.addPlayTime("Petya", 5);
        store.addPlayTime("Kolya", 3);
        store.addPlayTime("Katya", 4);

        String expected = "Petya";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test    //тест на поиск лучшего игрока, один из игроков играл в 2 разные игры - тест не проходит
    public void shouldGetMostPlayerWhenPlayedSeveralGames() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Neodash", "Аркады");

        store.addPlayTime("Petya", 5);
        store.addPlayTime("Kolya", 3);
        store.addPlayTime("Kolya", 3);

        String expected = "Kolya";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test   //тест на поиск лучшего игрока, когда нет игроков - тест проходит
    public void shouldGetMostPlayerWithoutPlayers() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        String expected = null;
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test  //тест на суммирование времени всех игроков, проведенного за играми - тест не проходит
    public void shouldGetSumPlayedTime() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        store.addPlayTime("Petya", 5);
        store.addPlayTime("Kolya", 3);
        store.addPlayTime("Katya", 4);

        int expected = 12;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }


    // другие ваши тесты
    //Здесь будут тесты напарника
}

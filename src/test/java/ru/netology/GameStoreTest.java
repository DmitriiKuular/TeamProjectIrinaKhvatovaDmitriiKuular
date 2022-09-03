package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameStoreTest {
    @Test   //тест позволяет добавить игру в каталог - не находит в каталоге добавленной игры - issue 9
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    @Test   //тест проверяет наличие игры в каталоге, тест проходит - такой игры в каталоге нет
    public void shouldReturnFalseAddGame() {

        GameStore store = new GameStore();
        Game game2 = new Game("Half-Life", "Шутеры", store);
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertFalse(store.containsGame(game2));
    }

    @Test   //тест на нахождение общего времени игры для одного игрока - не суммирует время - issue 8
    public void shouldAddPlayTime() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Gosha");
        player.installGame(game);
        player.play(game, 8);


        store.addPlayTime("Katya", 2);
        store.addPlayTime("Kolya", 3);
        store.addPlayTime("Katya", 4);
        store.addPlayTime("Petya", 5);


        String expected = "Gosha";


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

    @Test  //тест на поиск лучшего игрока из 3х разных игроков, если у 2х игроков одинаковое время - тест проходит
    public void shouldGetMostPlayerWhenSameHours() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        store.addPlayTime("Petya", 5);
        store.addPlayTime("Kolya", 5);
        store.addPlayTime("Katya", 4);

        String expected = "Petya";
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

    @Test  //тест на суммирование времени всех игроков, проведенного за играми - тест не проходит - issue 7
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

    @Test  //тест на поиск лучшего игрока, тест не проходит, если время игры равно 1 час - issue 11
    public void shouldGetMostPlayerIfGameTimeIsOneHour() {

        GameStore store = new GameStore();

        store.addPlayTime("Katya", 1);

        String actual = store.getMostPlayer();
        String expected = "Katya";
        assertEquals(expected, actual);
    }
    // другие ваши тесты
}
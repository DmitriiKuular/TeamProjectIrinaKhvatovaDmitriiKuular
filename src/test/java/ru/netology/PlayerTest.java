package ru.netology;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

public class PlayerTest {

    GameStore store = new GameStore();
    Player player = new Player("Petya");
    Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game1 = store.publishGame("Stalker", "Shooter");
    Game game2 = store.publishGame("Stalker 2", "Shooter");
    Game game3 = store.publishGame("Stalker 3", "Shooter");


    @Test
    public void shouldInstallGame() {
        player.installGame(game2);
        int expected = 0;
        int actual = player.play(game2,0);
        assertEquals(expected, actual);
    }

    @Test //Первый нерабочий тест. Обновляет часы в игре после переустановки
    public void shouldNotToChangePlayedTime() {
        player.installGame(game1);
        player.play(game1, 5);
        player.installGame(game1);
        int expected = 5;
        int actual = player.play(game1, 0);
        assertEquals(expected, actual);
    }

    @Test //Второй нерабочий тест. не сохраняет часы в игре
    public void shouldShowPlayedTimeOneGame() {
        Player player = new Player("Petya");
        player.installGame(game1);
        int expected = 8;
        int actual = player.play(game1, 8);
        assertEquals(expected, actual);
    }

    @Test //Третий нерабочий тест. не суммирует время в игре
    public void shouldSumPlayedTimeOneGame() {
        player.installGame(game3);
        player.play(game3, 5);
        int expected = 13;
        int actual = player.play(game3, 8);
        assertEquals(expected, actual);
    }

    @Test //Четвертый нерабочий тест.Не выдвет exception
    public void shouldShowException() {
        assertThrows(NotSetupException.class, () -> {player.play(game, 0);});
    }

    @Test //Пятый нерабочий тест. Не суммирует время одной игры жанра
    public void shouldSumGenreIfOneGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test //Шестой нерабочий тест. Не суммирует время нескольких игр одного жанра
    public void shouldSumGenreIfSeveralGames() {
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 15);
        player.play(game2, 5);
        player.play(game3, 8);
        player.play(game, 1);

        int expected = 28;
        int actual = player.sumGenre("Shooter");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotToSumNotInstallGame() {
        int expected = 0;
        int actual = player.sumGenre("Shooter");
        assertEquals(expected, actual);
    }

    @Test  //Седьмой нерабочий тест. Не возвращает игру жанра, в которую играли больше всего
    public void shouldShowMostPopularGame() {
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 15);
        player.play(game2, 16);
        player.play(game3, 18);
        player.play(game1, 9);
        Game expected = game1;
        Game actual = player.mostPlayerByGenre("Shooter");
        assertEquals(expected, actual);
    }

    @Test //Восьмой нерабочий тест. Не возвращает игру жанра, в которую играли больше всего (одна игра)
    public void shouldShowPopularGameIfOneGame() {
        player.installGame(game1);
        player.play(game1, 5);
        Game expected = game1;
        Game actual = player.mostPlayerByGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldNotToShowPopularGame() {
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        Game expected = null;
        Game actual = player.mostPlayerByGenre("Shooter");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldGetName() {
        String expected = "Petya";
        String actual = player.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldShowMostPopularGameIfEquals() {
        player.installGame(game);
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 15);
        player.play(game2, 16);
        player.play(game3, 12);
        player.play(game1, 1);
        Game expected = game2;
        Game actual = player.mostPlayerByGenre("Shooter");
        assertEquals(expected, actual);
    }
    // другие ваши тесты
}

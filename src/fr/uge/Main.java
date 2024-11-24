package fr.uge;

import fr.uge.game.Game;
import fr.uge.game.PlateauType;
import fr.uge.game.StartGame;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    var start = new StartGame(StartGame.chooseNumberOfPlayers(), StartGame.chooseGameVariant(), PlateauType.SQUARE);
    var Game = new Game(start);
  }
}

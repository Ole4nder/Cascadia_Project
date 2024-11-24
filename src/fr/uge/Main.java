package fr.uge;

import fr.uge.game.Game;
import fr.uge.game.PlateauType;
import fr.uge.game.StartGame;
import fr.uge.graphic.GraphicTerminal;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    var start =
        new StartGame(
            StartGame.chooseNumberOfPlayers(), StartGame.chooseGameVariant(), PlateauType.SQUARE);
    var game = new Game(start);

    game.startGame().initGame();
    System.out.println(game.startGame().players());
    for (var player : game.startGame().players()) {
      System.out.println(GraphicTerminal.drawTile(player.tileNeighborMap().keySet()));
    }
  }
}

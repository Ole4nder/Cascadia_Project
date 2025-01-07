package fr.uge.main;

import com.github.forax.zen.Application;
import fr.uge.animal.Animals;
import fr.uge.board.players.PlayerBoard;
import fr.uge.graphic.GraphicSquareZen;
import fr.uge.tile.*;
import java.awt.*;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    Application.run(
        Color.WHITE,
        applicationContext -> {
          var graphic = new GraphicSquareZen(applicationContext);
          graphic.drawCoordToPutTile(
              Set.of(
                  new TileCoord(0, -1),
                  new TileCoord(1, 1),
                  new TileCoord(1, 2),
                  new TileCoord(0, 3),
                  new TileCoord(-1, 2),
                  new TileCoord(-1, 1),
                  new TileCoord(-1, 0),
                  new TileCoord(1, 0)));
          var player = new PlayerBoard();
          player.add(
              new TileCoord(0, 0),
              new SquareTile(TileLandscape.FOREST, Animals.BEAR, Animals.ELK, Animals.DEFAULT));
          player.add(
              new TileCoord(0, 1),
              new SquareTile(TileLandscape.FOREST, Animals.BEAR, Animals.ELK, Animals.DEFAULT));
          player.add(
              new TileCoord(0, 2),
              new SquareTile(TileLandscape.FOREST, Animals.BEAR, Animals.ELK, Animals.DEFAULT));
          graphic.drawGameBoard(
              player.board(),
              player.getMinX(),
              player.getMaxX(),
              player.getMinY(),
              player.getMaxY());
        });
    //    var start =
    //        new InitGame(
    //            InitGame.chooseNumberOfPlayers(), InitGame.chooseGameVariant(), BoardType.SQUARE);
    //    var game = new Game(start, new GraphicTerminal(), new TerminalInteraction());
    //    game.startGame().initGame();
    //    game.play();
  }
}

package fr.uge.main;

import com.github.forax.zen.Application;
import fr.uge.animal.Animals;
import fr.uge.board.players.PlayerBoard;
import fr.uge.graphic.GraphicSquareZen;
import fr.uge.interaction.ZenInteractionSquare;
import fr.uge.stack.StackList;
import fr.uge.stack.StackTileToken;
import fr.uge.tile.*;
import java.awt.*;
import java.util.Set;

public class Main {
  public static void main(String[] args) {
    Application.run(
        Color.DARK_GRAY,
        applicationContext -> {
          var graphic = new GraphicSquareZen(applicationContext);
          var setCoord =
              Set.of(
                  new TileCoord(0, -1),
                  new TileCoord(1, 1),
                  new TileCoord(1, 2),
                  new TileCoord(0, 3),
                  new TileCoord(-1, 2),
                  new TileCoord(-1, 1),
                  new TileCoord(-1, 0),
                  new TileCoord(1, 0));
          graphic.drawCoordToPutTile(setCoord);
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
          var stack = new StackList();
          stack.add(
              new StackTileToken(
                  new SquareTile(TileLandscape.FOREST, Animals.BEAR, Animals.ELK, Animals.DEFAULT),
                  Animals.BEAR));
          stack.add(
              new StackTileToken(
                  new SquareTile(TileLandscape.FOREST, Animals.BEAR, Animals.ELK, Animals.DEFAULT),
                  Animals.ELK));
          stack.add(
              new StackTileToken(
                  new SquareTile(TileLandscape.FOREST, Animals.BEAR, Animals.ELK, Animals.DEFAULT),
                  Animals.SALMON));
          stack.add(
              new StackTileToken(
                  new SquareTile(TileLandscape.FOREST, Animals.BEAR, Animals.ELK, Animals.DEFAULT),
                  Animals.FOX));
          // graphic.drawOptionTileToken(stack);
          var stackOption =
              new ZenInteractionSquare(applicationContext).choiceOption(stack, graphic);
          while (true) {
            var tileToPutTOken = new ZenInteractionSquare(applicationContext).wantChangeOption();
            System.out.println(tileToPutTOken);
          }
        });
    //    var start =
    //        new InitGame(
    //            InitGame.chooseNumberOfPlayers(), InitGame.chooseGameVariant(), BoardType.SQUARE);
    //    var game = new Game(start, new GraphicTerminal(), new TerminalInteraction());
    //    game.startGame().initGame();
    //    game.play();
  }
}

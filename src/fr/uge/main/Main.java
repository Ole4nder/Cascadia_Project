package fr.uge.main;

import fr.uge.board.BoardType;
import fr.uge.game.Game;
import fr.uge.game.InitGame;
import fr.uge.graphic.GraphicTerminal;
import fr.uge.interaction.TerminalInteraction;
import java.io.IOException;

public class Main {
  public static void main() throws IOException {
    var start =
        new InitGame(
            InitGame.chooseNumberOfPlayers(), InitGame.chooseGameVariant(), BoardType.SQUARE);
    var game = new Game(start, new GraphicTerminal(), new TerminalInteraction());
    game.startGame().initGame();
    System.out.println(game.startGame().animalTokenBag().tokenBag());
    System.out.println(game.startGame().tileBag().tileBag());
    for (var player : game.startGame().playersBoards().playerBoardsList()) {
      System.out.println(GraphicTerminal.drawAllTiles(player.tileNeighborMap().keySet()));
      //System.out.println(game.wherePutTile(player));
      //System.out.println(game.wherePutToken(player));
    }
  }
}

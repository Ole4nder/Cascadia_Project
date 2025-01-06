package fr.uge.main;

import com.github.forax.zen.Application;
import fr.uge.board.BoardType;
import fr.uge.game.InitGame;
import fr.uge.graphic.GraphicSquareZen;

import java.awt.*;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    Application.run(Color.WHITE, applicationContext -> {
      var start = new InitGame(InitGame.chooseNumberOfPlayers(), InitGame.chooseGameVariant(), BoardType.SQUARE);
      var graphic = new GraphicSquareZen();
      graphic.drawGameBoard(start.playersBoards().playerBoardsList().getFirst().board(), 0, 0, 0, 0);

    });
//    var start =
//        new InitGame(
//            InitGame.chooseNumberOfPlayers(), InitGame.chooseGameVariant(), BoardType.SQUARE);
//    var game = new Game(start, new GraphicTerminal(), new TerminalInteraction());
//    game.startGame().initGame();
//    game.play();
  }
}

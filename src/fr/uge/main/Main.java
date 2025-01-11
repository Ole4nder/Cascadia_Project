package fr.uge.main;

import com.github.forax.zen.Application;
import com.github.forax.zen.ApplicationContext;
import fr.uge.board.BoardType;
import fr.uge.game.Game;
import fr.uge.game.InitGame;
import fr.uge.graphic.GraphicSquareZen;
import fr.uge.graphic.GraphicTerminal;
import fr.uge.interaction.TerminalInteraction;
import fr.uge.interaction.ZenInteractionSquare;
import java.awt.*;
import java.io.IOException;

public class Main {
  public static Game gameInit(
      InitGame initGame, String type, ApplicationContext applicationContext) {
    return switch (type) {
      case "terminal" -> new Game(initGame, new GraphicTerminal(), new TerminalInteraction());
      case "zen" ->
          new Game(
              initGame,
              new GraphicSquareZen(applicationContext),
              new ZenInteractionSquare(applicationContext));
      default -> throw new IllegalArgumentException("Invalid type of graphic!");
    };
  }

  public static void main() {
    Application.run(
        Color.DARK_GRAY,
        applicationCOntext -> {
          var start =
              new InitGame(
                  InitGame.chooseNumberOfPlayers(), InitGame.chooseGameVariant(), BoardType.SQUARE);
          var game =
              gameInit(
                  start, InitGame.chooseGraphicVariant(), applicationCOntext);
          try {
            game.startGame().initGame();
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          game.play();
          start.playersBoards().updateScoreBoard(start.gameType());
          System.out.println(start.playersBoards().printScoreBoard());
        });
  }
}

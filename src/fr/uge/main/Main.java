package fr.uge.main;

import fr.uge.board.BoardType;
import fr.uge.game.Game;
import fr.uge.game.InitGame;
import fr.uge.graphic.GraphicTerminal;
import fr.uge.interaction.TerminalInteraction;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws IOException {
    var start =
        new InitGame(
            InitGame.chooseNumberOfPlayers(), InitGame.chooseGameVariant(), BoardType.SQUARE);
    var game = new Game(start, new GraphicTerminal(), new TerminalInteraction());
    game.startGame().initGame();
    game.play();
  }
}

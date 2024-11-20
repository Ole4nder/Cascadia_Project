package fr.uge;

import java.io.IOException;

import fr.uge.game.GameType;
import fr.uge.game.StartGame;

public class Main {

  public static void main(String[] args) {
  	var start = new StartGame(2, GameType.FAMILY);
  	try {
			start.initGame();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  }
}

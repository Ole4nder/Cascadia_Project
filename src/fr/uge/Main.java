package fr.uge;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import fr.uge.animal.Animals;
import fr.uge.game.GameType;
import fr.uge.game.StartGame;

public class Main {

  public static void main(String[] args) {
  	int playersNumbers = StartGame.chooseNumberOfPlayers();
    GameType selectedGameType = StartGame.chooseGameVariant();
    StartGame start = new StartGame(playersNumbers, selectedGameType);
//  	StartGame start = new StartGame(2, GameType.FAMILY);	//TODO: a enlever, juste flemme de choisir à chaque fois
    
    try {
      start.initGame();

      List<Animals> drawnTokens = Arrays.asList(Animals.FOX, Animals.FOX, Animals.FOX, Animals.FOX);
      System.out.println("Pioche1 : " + drawnTokens);
      List<Animals> overpopulatedTokens = start.overpopulation(drawnTokens);
      System.out.println("Nouvelle pioche1 " + overpopulatedTokens + "\n");
      
      List<Animals> drawnTokens2 = start.drawTokens(4);
      System.out.println("Pioche2 : " + drawnTokens2);
      List<Animals> overpopulatedTokens2 = start.overpopulation(drawnTokens2);	//TODO: a enlever, explication : Même liste si au moins 1 différent
      System.out.println("Nouvelle pioche2 " + overpopulatedTokens2);
      
      System.out.println("\nNombre de tokens différents dans la nouvelle pioche1 : " + start.distinctTokens(overpopulatedTokens));
      System.out.println("Nombre de tokens différents dans la nouvelle pioche2 : " + start.distinctTokens(overpopulatedTokens2));

    } catch (IOException e) {
      System.out.println("Error initializing the game: " + e.getMessage());
    }
  }
}

package fr.uge;

import fr.uge.animal.Animals;
import fr.uge.game.GameType;
import fr.uge.game.StartGame;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
  /**
   * Ask the player which variant he wants to play.
   *
   * @return GameType, variant chosen by the player.
   */


  public static void main(String[] args) throws IOException {
    var playersNumbers = StartGame.chooseNumberOfPlayers();
    var selectedGameType = StartGame.chooseGameVariant();
    var start = new StartGame(playersNumbers, selectedGameType);
    //  	StartGame start = new StartGame(2, GameType.FAMILY);	//TODO: a enlever, juste flemme de
    // choisir à chaque fois
    start.initGame();

//    List<Animals> drawnTokens = Arrays.asList(Animals.FOX, Animals.FOX, Animals.FOX, Animals.FOX);
//    System.out.println("Pioche1 : " + drawnTokens);
//    List<Animals> overpopulatedTokens = start.overpopulation(drawnTokens);
//    System.out.println("Nouvelle pioche1 " + overpopulatedTokens + "\n");
//
//    List<Animals> drawnTokens2 = start.drawTokens(4);
//    System.out.println("Pioche2 : " + drawnTokens2);
//    List<Animals> overpopulatedTokens2 =
//        start.overpopulation(
//            drawnTokens2); // TODO: a enlever, explication : Même liste si au moins 1 différent
//    System.out.println("Nouvelle pioche2 " + overpopulatedTokens2);
//
//    System.out.println(
//        "\nNombre de tokens différents dans la nouvelle pioche1 : "
//            + start.distinctTokens(overpopulatedTokens));
//    System.out.println(
//        "Nombre de tokens différents dans la nouvelle pioche2 : "
//            + start.distinctTokens(overpopulatedTokens2));
  }
}

package fr.uge.score;

import fr.uge.board.players.PlayerBoard;

public class ScoreCard {
  public static int scoreFamilyCard(PlayerBoard playerBoard) {
    return ScorePlayerBoard.findLargestLandscape(playerBoard.board()).values().stream()
        .mapToInt(Integer::intValue)
        .sum();
  }

  public static int scoreIntermediateCard(PlayerBoard playerBoard) {
    var bestLandscape =
        ScorePlayerBoard.findLargestLandscape(playerBoard.board()).values().stream()
            .mapToInt(Integer::intValue)
            .max()
            .orElse(0);
    return switch (bestLandscape) {
      case 1 -> 2;
      case 2 -> 5;
      case 3 -> 9;
      case 4 -> 12;
      default -> 0;
    };
  }
}

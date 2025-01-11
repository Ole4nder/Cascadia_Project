package fr.uge.board.players;

import fr.uge.game.GameType;
import fr.uge.score.ScoreCard;
import fr.uge.score.ScorePlayerBoard;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/** Represent all players boards for the game. */
public class PlayersBoards {
  private final List<PlayerBoard> playersBoardsList = new ArrayList<>();

  /**
   * Add a player board.
   *
   * @param playerBoard the player board to add.
   */
  public void add(PlayerBoard playerBoard) {
    Objects.requireNonNull(playerBoard);
    playersBoardsList.add(playerBoard);
  }

  private List<Integer> getScoreBoardForALlPlayers() {
    return playersBoardsList.stream()
        .map(PlayerBoard::board)
        .map(ScorePlayerBoard::findLargestLandscape)
        .map(landscapeSizes -> landscapeSizes.values().stream().max(Integer::compareTo).orElse(0))
        .collect(Collectors.toList());
  }

  public void updateScoreBoard(GameType gameType) {
    var scores = getScoreBoardForALlPlayers();
    IntStream.range(0, playersBoardsList.size())
        .forEach(i -> playersBoardsList.get(i).updateScore(scores.get(i)));
    if (gameType == GameType.FAMILY) {
      playersBoardsList.forEach(
          playerBoard -> playerBoard.updateScore(ScoreCard.scoreFamilyCard(playerBoard)));
    } else {
      playersBoardsList.forEach(
          playerBoard -> playerBoard.updateScore(ScoreCard.scoreIntermediateCard(playerBoard)));
    }
  }

  public String printScoreBoard() {
    return playersBoardsList.stream().map(PlayerBoard::toString).collect(Collectors.joining("\n"));
  }

  /**
   * Return a copy of the current list.
   *
   * @return List of player boards
   */
  public List<PlayerBoard> playerBoardsList() {
    return List.copyOf(playersBoardsList);
  }
}

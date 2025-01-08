package fr.uge.board.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import score.ScorePlayerBoard;

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

  public void updateScoreForPlayers() {
    var finalBestScore =
        playersBoardsList.stream()
            .peek(
                playerBoard ->
                    playerBoard.updateScore(
                        ScorePlayerBoard.findLargestLandscape(playerBoard.board())))
            .mapToInt(PlayerBoard::score)
            .max()
            .orElse(0);

    playersBoardsList.stream()
        .filter(playerBoard -> playerBoard.score() == finalBestScore)
        .findFirst()
        .ifPresent(playerBoard -> playerBoard.updateScore(finalBestScore));
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

package fr.uge.board.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

  /**
   * Remove a player board.
   *
   * @param playerBoard the player board to remove.
   */
  public void remove(PlayerBoard playerBoard) {
    Objects.requireNonNull(playerBoard);
    playersBoardsList.remove(playerBoard);
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

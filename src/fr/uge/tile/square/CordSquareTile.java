package fr.uge.tile.square;

/**
 * Represents the coordinates of a square tile on the board.
 *
 * @param upLeft if they value is -1 the tile is not on the board (tile bag)
 * @param downRight if they value is -1 the tile is not on the board (tile bag)
 */
public record CordSquareTile(int upLeft, int downRight) {
  public CordSquareTile {
    if (downRight < -1 || upLeft < -1) {
      throw new IllegalArgumentException(
          "CordSquareTile: downRight and upLeft must be positive or equals to -1");
    }
  }

  public CordSquareTile changeCordSquareTile(int newUpLeft, int newDownRight) {
    return new CordSquareTile(newUpLeft, newDownRight);
  }
}

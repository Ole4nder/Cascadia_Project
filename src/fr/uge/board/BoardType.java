package fr.uge.board;

/** different types of plateau with number of sides for each case. */
// TODO : remove this class and use directly the enum BoardType
public enum BoardType {
  SQUARE(4),
  HEXAGON(6);
  private final int value;

  BoardType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}

package fr.uge.tile.square;

import java.util.Comparator;

/** Represents the coordinates of a square tile on the board. */
public class CordSquareTile {
  public static final Comparator<CordSquareTile> COMPARATOR =
      Comparator.comparingInt(CordSquareTile::upLeft).thenComparingInt(CordSquareTile::downRight);
  private int upLeft;
  private int downRight;

  public CordSquareTile(int upLeft, int downRight) {
    this.upLeft = upLeft;
    this.downRight = downRight;
  }

  public int downRight() {
    return downRight;
  }

  public int upLeft() {
    return upLeft;
  }

  public void changeCordSquareTile(int newUpLeft, int newDownRight) {
    this.upLeft = newUpLeft;
    this.downRight = newDownRight;
  }
}

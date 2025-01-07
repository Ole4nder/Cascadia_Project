package fr.uge.stack;

import fr.uge.animal.Animals;
import fr.uge.tile.Tile;
import java.util.Objects;

/**
 * A class representing a tile with a token.
 */
public class StackTileToken {
  private final Tile tile;
  private Animals token;

  public StackTileToken(Tile tile, Animals token) {
    Objects.requireNonNull(tile);
    Objects.requireNonNull(token);
    this.tile = tile;
    this.token = token;
  }

  /**
   * Change the token.
   *
   * @param token the new token.
   */
  public void changeToken(Animals token) {
    Objects.requireNonNull(token);
    this.token = token;
  }

  /**
   * Get the token.
   *
   * @return the token.
   */
  public Animals token() {
    return token;
  }

  /**
   * Get the tile of the token.
   *
   * @return the tile.
   */
  public Tile tile() {
    return tile;
  }

  @Override
  public String toString() {
    return "OptionTileToken{" + "tile=" + tile + ", token=" + token + '}';
  }
}

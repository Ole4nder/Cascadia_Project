package fr.uge.tile;

import fr.uge.animal.Animals;
import fr.uge.tile.coord.Coord;

public interface Tile {
  Coord coord();

  public boolean setAnimalToken(Animals animal);

  Animals animalToken();

  String landscape();

  Animals animals1();

  Animals animals2();

  int neighborPosition(Tile tile);

}

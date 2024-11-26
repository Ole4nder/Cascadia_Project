package fr.uge.tile;

import fr.uge.animal.Animals;

public interface Tile {
  TileCoord coord();

  boolean setAnimalToken(Animals animal);

  Animals animalToken();

  String landscape();

  Animals animals1();

  Animals animals2();

  int neighborPosition(Tile tile);

}

package fr.uge.tile;

import fr.uge.animal.Animals;

public interface Tile {
  TileCoord coord();

  void setAnimalToken(Animals animal);

  Animals animalToken();

  TileLandscape landscape();

  Animals animals1();

  Animals animals2();

  int neighborPosition(Tile tile);

}

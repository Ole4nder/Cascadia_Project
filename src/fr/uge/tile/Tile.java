package fr.uge.tile;

import fr.uge.animal.Animals;
import fr.uge.tile.square.CordSquareTile;

public interface Tile {
    CordSquareTile cord(); // TODO changer cette méthode une fois l'implémentation des hexagones faite

    public boolean setAnimalToken(Animals animal);


    Animals animalToken();

    int numberNeighbors();
}

package fr.uge.player;

import fr.uge.tile.DepartTile;
import fr.uge.tile.Tile;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class Player {
    private final DepartTile departTile;
    private final Map<Tile, HashSet<Tile>> tileNeighborMap = new HashMap<>();

    public Player(DepartTile departTile) {
        Objects.requireNonNull(departTile);
        this.departTile = departTile;
    }

    public void add(Tile tile, Tile neighbor) {
        Objects.requireNonNull(tile);
        Objects.requireNonNull(neighbor);
        tileNeighborMap.computeIfAbsent(tile, _ -> new HashSet<>()).add(neighbor);
    }
}

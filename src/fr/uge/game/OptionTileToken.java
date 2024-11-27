package fr.uge.game;

import fr.uge.animal.Animals;
import fr.uge.tile.Tile;

import java.util.Objects;

public record OptionTileToken(Tile tile, Animals token) {
    public OptionTileToken {
        Objects.requireNonNull(tile);
        Objects.requireNonNull(token);
    }
}

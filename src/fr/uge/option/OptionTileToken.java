package fr.uge.option;

import fr.uge.animal.Animals;
import fr.uge.tile.Tile;
import java.util.Objects;

public class OptionTileToken {
    private final Tile tile;
    private Animals token;

    public OptionTileToken(Tile tile, Animals token) {
        Objects.requireNonNull(tile);
        Objects.requireNonNull(token);
        this.tile = tile;
        this.token = token;
    }

    public void changeToken(Animals token) {
        Objects.requireNonNull(token);
        this.token = token;
    }

    public Animals token() {
        return token;
    }

    public Tile tile() {
        return tile;
    }

    @Override
    public String toString() {
        return "OptionTileToken{" + "tile=" + tile + ", token=" + token + '}';
    }
}

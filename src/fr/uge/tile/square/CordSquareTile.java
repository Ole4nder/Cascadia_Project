package fr.uge.tile.square;

public record CordSquareTile(int upLeft, int downRight) {
    public CordSquareTile {
        if (downRight < 0 || upLeft < 0) {
            throw new IllegalArgumentException("CordSquareTile: downRight and upLeft must be positive");
        }
    }
}

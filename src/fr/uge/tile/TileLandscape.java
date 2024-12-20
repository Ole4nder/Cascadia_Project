package fr.uge.tile;

public enum TileLandscape {
    RIVER("river"),
    SWAMP("swamp"),
    MOUNTAIN("mountain"),
    FOREST("forest"),
    PASTURE("pasture"),
    DEFAULT("default")
    ;
    private final String landscape;
    TileLandscape(String landscape) {
        this.landscape = landscape;
    }
    public String getLandscape() {
        return landscape;
    }

    /**
     * Convert a string to a TileLandscape enum.
     * @param landscape the string to convert.
     * @return the corresponding TileLandscape enum.
     */
    public static TileLandscape landscapeNameToEnums(String landscape) {
        return switch (landscape) {
            case "rivières" -> TileLandscape.RIVER;
            case "marais" -> TileLandscape.SWAMP;
            case "montagnes" -> TileLandscape.MOUNTAIN;
            case "forêts" -> TileLandscape.FOREST;
            case "prairies" -> TileLandscape.PASTURE;
            default -> throw new IllegalStateException("Unexpected value: " + landscape);
        };
    }
}

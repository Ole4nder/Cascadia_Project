package fr.uge.animal;

/** Different types of animals in the game. */
public enum Animals {
  SALMON("salmon"),
  BEAR("bear"),
  FOX("fox"),
  HAWK("hawk"),
  ELK("elk"),
  DEFAULT("default");

  private final String name;
  Animals(String name) {
    this.name = name;
  }

  /**
   * Get animal's name from file and turn them into their enum.
   *
   * @param animal the animal name
   * @return Animals
   */
  public static Animals animalNameToEnums(String animal) {
    return switch (animal) {
      case "bear" -> Animals.BEAR;
      case "elk" -> Animals.ELK;
      case "fox" -> Animals.FOX;
      case "salmon" -> Animals.SALMON;
      case "hawk" -> Animals.HAWK;
      case "default" -> Animals.DEFAULT;
      default -> throw new IllegalStateException("Unexpected value: " + animal);
    };
  }

    public String getName() {
        return name;
    }
}

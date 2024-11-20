package fr.uge.animal.card;

import java.util.Objects;

public record AnimalCard(TypeCard type, String description) {
	public AnimalCard {
		Objects.requireNonNull(description);
		Objects.requireNonNull(type);
	}
}

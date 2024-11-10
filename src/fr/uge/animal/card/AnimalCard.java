package fr.uge.animal.card;

import java.util.Objects;

public record AnimalCard(String description, boolean familialVariant, TypeCard type) {
	public AnimalCard {
		Objects.requireNonNull(description);
		Objects.requireNonNull(type);
	}
}

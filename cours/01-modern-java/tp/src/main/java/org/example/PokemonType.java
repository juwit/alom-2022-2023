package org.example;

import java.util.List;

public record PokemonType(int id, String name, List<String> types) {
}

package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PokemonStreams {

    public List<PokemonType> sortById(){
        return this.pokemonsTypes.stream()
                .sorted(Comparator.comparing(PokemonType::id))
                .toList();
    }

    private Collection<PokemonType> pokemonsTypes;
    public void loadPokemonTypes() throws IOException {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        this.pokemonsTypes = objectMapper.readValue(new FileInputStream("src/main/resources/pokemons.json"), new TypeReference<Collection<PokemonType>>() {});
    }

    public Set<PokemonType> findByType(String type) {
        return this.pokemonsTypes.stream()
                .filter(it -> it.types().contains(type))
                .collect(Collectors.toSet());
    }

    public Optional<PokemonType> findFirstByTypes(String... types) {
        return this.pokemonsTypes.stream()
                .sorted(Comparator.comparing(PokemonType::id))
                .filter(it -> it.types().containsAll(List.of(types)))
                .findFirst();
    }
}

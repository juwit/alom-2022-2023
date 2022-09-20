import org.example.PokemonStreams;
import org.example.PokemonType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class StreamsTest {

    PokemonStreams pokemonStreams;

    @BeforeEach
    void setUp() throws IOException {
        pokemonStreams = new PokemonStreams();
        pokemonStreams.loadPokemonTypes();
    }

    @Test
    public void testSortById(){
        assertThat(pokemonStreams.sortById())
                .extracting(it -> it.id())
                .isSorted();
    }

    @Test
    public void testFindElectricPokemons(){
        assertThat(pokemonStreams.findByType("electric"))
                .hasSize(9);
    }

    @Test
    public void testFindFirePokemons(){
        assertThat(pokemonStreams.findByType("fire"))
                .hasSize(12);
    }

    @Test
    public void testFindFirstPsychicPokemon(){
        assertThat(pokemonStreams.findFirstByTypes("psychic"))
                .isNotEmpty()
                .get()
                .extracting("name")
                .isEqualTo("abra");
    }
}

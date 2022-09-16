import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.PokemonType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RecordsTest {

    record Pokemon(PokemonType type, String nickname, int level) {
    }

    @Test
    public void testPokemonTypeCreation() {
        var pikachu = new PokemonType(25, "Pikachu", List.of("electric"));

        assertThat(pikachu.id()).isEqualTo(25);
        assertThat(pikachu.name()).isEqualTo("Pikachu");
        assertThat(pikachu.types()).containsOnly("electric");
    }

    @Test
    public void testPokemonCreation() {
        var geodude = new PokemonType(74, "Racaillou", List.of("rock", "ground"));
        var petersGeodude = new Pokemon(geodude, "Racaillou de Pierre", 12);

        assertThat(petersGeodude.nickname()).isEqualTo("Racaillou de Pierre");
        assertThat(petersGeodude.type()).isEqualTo(geodude);
        assertThat(petersGeodude.level()).isEqualTo(12);
    }

    @Test
    public void testLoadPokemonFromJSON() throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        var leviathanJSON = """
                {
                    "id": 130,
                    "name": "gyarados",
                    "baseExperience": 189,
                    "weight": 2350,
                    "height": 65,
                    "types": [
                      "flying",
                      "water"
                    ],
                    "stats": {
                      "speed": 81,
                      "attack": 125,
                      "defense": 79,
                      "hp": 95
                    },
                    "sprites": {
                      "front_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/130.png",
                      "back_default": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/130.png"
                    }
                  }
                """;

        var leviathan = objectMapper.readValue(leviathanJSON, PokemonType.class);
        assertThat(leviathan.id()).isEqualTo(130);
        assertThat(leviathan.name()).isEqualTo("gyarados");
        assertThat(leviathan.types()).contains("flying", "water");
    }

}

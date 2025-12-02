package edu.hw8.Task3;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.*;

public class DefaultDatabaseHackerTest {

    private static Stream<Arguments> inputsForHackTest() {
        return Stream.of(
            Arguments.of(
                new HashMap<String, String>() {{
                    put("9627df7a4a5b849f67fce863e82adc71", "a.v.petrov");
                    put("0ba4439ee9a46d9d9f14c60f88f45f87", "v.v.belov");
                }},
                new HashMap<String, String>() {{
                    put("a.v.petrov", "testi");
                    put("v.v.belov", "check");
                }}
            )
        );
    }

    @ParameterizedTest
    @MethodSource("inputsForHackTest")
    @DisplayName("#hack test")
    public void hack_shouldReturnMapWithUsernameAndHackedPassword(
        Map<String, String> db,
        Map<String, String> expected
    ) {
        DatabaseHacker hacker = new DefaultDatabaseHacker(db);
        Map<String, String> actual = hacker.hack();
        assertThat(actual).containsExactlyInAnyOrderEntriesOf(expected);
    }
}

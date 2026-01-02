package edu.basics5;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task7Test {

    static final Map<String, List<Boolean>> VALIDATION_MACHINE_ALPHABET = Map.of(
        "10101010", List.of(false, false, false),
        "0100000", List.of(true, true, false),
        "00011110000", List.of(true, true, false),
        "1", List.of(false, false, true),
        "0", List.of(false, false, true),
        "111", List.of(false, true, true)
    );

    @ParameterizedTest
    @ValueSource(strings = {"10101010", "0100000", "00011110000", "1", "0", "111"})
    void testEx1(String argument) {
        // given
        // when
        Boolean resultValid = Task7.validateThirdZero(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(VALIDATION_MACHINE_ALPHABET.get(argument).getFirst());
    }

    @ParameterizedTest
    @ValueSource(strings = {"10101010", "0100000", "00011110000", "1", "0", "111"})
    void testEx2(String argument) {
        // given
        // when
        Boolean resultValid = Task7.validateStartEqualsEnd(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(VALIDATION_MACHINE_ALPHABET.get(argument).get(1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10101010", "0100000", "00011110000", "1", "0", "111"})
    void testEx3(String argument) {
        // given
        // when
        Boolean resultValid = Task7.validateLengthOneToThree(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(VALIDATION_MACHINE_ALPHABET.get(argument).get(2));
    }

}

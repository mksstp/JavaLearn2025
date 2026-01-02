package edu.basics5;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task8Test {

    static final Map<String, List<Boolean>> VALIDATION_MACHINE_ALPHABET = Map.of(
        "10101010", List.of(false, true, false, true, true, false, true),
        "0100000", List.of(true, true, true, true, false, true, true),
        "00011110000", List.of(true, true, false, true, false, false, false),
        "1", List.of(true, false, true, true, true, false, true),
        "0", List.of(true, true, false, true, false, false, true),
        "111", List.of(true, false, true, false, true, false, false)
    );

    @ParameterizedTest
    @ValueSource(strings = {"10101010", "0100000", "00011110000", "1", "0", "111"})
    void testEx1(String argument) {
        // given
        // when
        Boolean resultValid = Task8.validateOddLength(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(VALIDATION_MACHINE_ALPHABET.get(argument).getFirst());
    }

    @ParameterizedTest
    @ValueSource(strings = {"10101010", "0100000", "00011110000", "1", "0", "111"})
    void testEx2(String argument) {
        // given
        // when
        Boolean resultValid = Task8.validateZeroOddOrOneEven(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(VALIDATION_MACHINE_ALPHABET.get(argument).get(1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10101010", "0100000", "00011110000", "1", "0", "111"})
    void testEx3(String argument) {
        // given
        // when
        Boolean resultValid = Task8.validateCountZeroDividedByThree(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(VALIDATION_MACHINE_ALPHABET.get(argument).get(2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10101010", "0100000", "00011110000", "1", "0", "111"})
    void testEx4(String argument) {
        // given
        // when
        Boolean resultValid = Task8.validateException11and111(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(VALIDATION_MACHINE_ALPHABET.get(argument).get(3));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10101010", "0100000", "00011110000", "1", "0", "111"})
    void testEx5(String argument) {
        // given
        // when
        Boolean resultValid = Task8.validateOddEqualsOne(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(VALIDATION_MACHINE_ALPHABET.get(argument).get(4));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10101010", "0100000", "00011110000", "1", "0", "111"})
    void testEx6(String argument) {
        // given
        // when
        Boolean resultValid = Task8.validateMin2ZerosAndMax1One(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(VALIDATION_MACHINE_ALPHABET.get(argument).get(5));
    }

    @ParameterizedTest
    @ValueSource(strings = {"10101010", "0100000", "00011110000", "1", "0", "111"})
    void testEx7(String argument) {
        // given
        // when
        Boolean resultValid = Task8.validateNoneSeqOfOne(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(VALIDATION_MACHINE_ALPHABET.get(argument).get(6));
    }

}

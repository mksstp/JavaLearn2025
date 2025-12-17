package edu.basics;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PalindromeDescendantTest {

    @ParameterizedTest
    @ValueSource(strings = {"24542","11211230","13001120","23336014"})
    void testCorrectInputTrue(String argument) {
        // given

        // when
        Boolean isDescendant = PalindromeDescendant.isPalindromeDescendant(argument);

        // then
        Assertions.assertThat(isDescendant).isEqualTo(true);
    }
    @ParameterizedTest
    @ValueSource(strings = {"245523531521"})
    void testCorrectInputFalse(String argument) {
        // given

        // when
        Boolean isDescendant = PalindromeDescendant.isPalindromeDescendant(argument);

        // then
        Assertions.assertThat(isDescendant).isEqualTo(false);
    }
}

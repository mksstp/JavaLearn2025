package edu.basics5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task4Test {

    @ParameterizedTest
    @ValueSource(strings = {"password$", "pass%word", "%password"})
    void testCorrectInput(String argument) {
        // given
        // when
        Boolean resultValid = Task4.passwordChecker(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"password"})
    void testUncorrectPassword(String argument) {
        // given
        // when
        Boolean resultValid = Task4.passwordChecker(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(false);
    }

}

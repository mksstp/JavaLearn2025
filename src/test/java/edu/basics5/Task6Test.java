package edu.basics5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task6Test {

    @ParameterizedTest
    @ValueSource(strings = {"abc", "aaaaaa", "afgg", "gcaa"})
    void testCorrectInput(String argument) {
        // given
        // when
        Boolean resultValid = Task6.subChecker(argument, "achfdbaabgabcaabg");
        // then
        Assertions.assertThat(resultValid).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"maac", "afmgb", "mac"})
    void testUncorrectNumber(String argument) {
        // given
        // when
        Boolean resultValid = Task6.subChecker(argument, "achfdbaabgabmcaabg");
        // then
        Assertions.assertThat(resultValid).isEqualTo(false);
    }

}

package edu.basics5;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Task5Test {

    @ParameterizedTest
    @ValueSource(strings = {"А123ВЕ777", "О777ОО177", "А123ВЕ77", "А123ВЕ100"})
    void testCorrectInput(String argument) {
        // given
        // when
        Boolean resultValid = Task5.checkValidCarNumber(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123АВЕ777", "А123ВГ77", "А123ВЕ7777", "А000ВГ77", "А123ВЕ000", "А123ВЕ00"})
    void testUncorrectNumber(String argument) {
        // given
        // when
        Boolean resultValid = Task5.checkValidCarNumber(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(false);
    }

}

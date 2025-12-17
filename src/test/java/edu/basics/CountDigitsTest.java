package edu.basics;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CountDigitsTest {
    @ParameterizedTest
    @ValueSource(strings = {"-346346161"})
    void countDigits(Integer argument) {
        // given

        // when
        Integer countDigits = CountDigits.countDigits(argument);

        // then
        Assertions.assertThat(countDigits).isEqualTo(9);
    }
}

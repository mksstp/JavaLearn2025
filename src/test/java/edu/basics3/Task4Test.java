package edu.basics3;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Map;

class Task4Test {

    static Map<Integer, String> dictionaryConvertedNumber = Map.of(2, "II", 12, "XII",
        16, "XVI", 3999, "MMMCMXCIX"
    );

    @ParameterizedTest
    @ValueSource(ints = {2, 12, 16, 3999})
    void testCorrectInput(Integer argument) {
        // given
        // when
        String convertedNumber = Task4.convertToRoman(argument);

        // then
        Assertions.assertThat(convertedNumber).isEqualTo(dictionaryConvertedNumber.get(argument));
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = {0, 4000})
    void testUncorrectInput(Integer argument) {
        Assertions.assertThatThrownBy(() ->
            Task4.convertToRoman(argument)).isInstanceOf(IllegalArgumentException.class);
    }

}

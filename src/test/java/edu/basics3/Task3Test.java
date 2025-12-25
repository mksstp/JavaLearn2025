package edu.basics3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class Task3Test {

    @Test
    @DisplayName("Проверка корректного вызова")
    void correctInputStr() {
        // given
        List<String> enterString = List.of("a", "bb", "a", "bb");

        // when
        Map<?, Integer> resultDictionary = Task3.freqDict(enterString);
        // then
        Assertions.assertThat(resultDictionary).isEqualTo(new HashMap<String, Integer>() {{
            put("bb", 2);
            put("a", 2);
        }});
    }

    @Test
    @DisplayName("Проверка корректного вызова")
    void correctInputInt() {
        // given
        List<Integer> enterInteger = List.of(1, 1, 2, 2);

        // when
        Map<?, Integer> resultDictionary = Task3.freqDict(enterInteger);
        // then
        Assertions.assertThat(resultDictionary).isEqualTo(new HashMap<Integer, Integer>() {{
            put(1, 2);
            put(2, 2);
        }});
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testUncorrectInput(List<?> argument) {
        Assertions.assertThatThrownBy(() ->
            Task3.freqDict(argument)).isInstanceOf(IllegalArgumentException.class);
    }

}

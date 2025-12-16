package edu.basics;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BrokenStringTest {


    @Test
    void testCorrectInput() {
        // given
        String testString = "hTsii  s aimex dpus rtni.g";
        // when
        String resultFixString = BrokenString.fixString(testString);

        // then
        Assertions.assertThat(resultFixString).isEqualTo("This is a mixed up string.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"22:45"})
    void testCorrectInput(String argument) {
        // given

        // when
        Integer timeInSeconds = VideoLength.minutesToSeconds(argument);

        // then
        Assertions.assertThat(timeInSeconds).isEqualTo(1365);
    }

}

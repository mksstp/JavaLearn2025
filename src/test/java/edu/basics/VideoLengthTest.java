package edu.basics;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class VideoLengthTest {

    @ParameterizedTest
    @ValueSource(strings = {"22:45"})
    void testCorrectInput(String argument) {
        // given

        // when
        Integer timeInSeconds = VideoLength.minutesToSeconds(argument);

        // then
        Assertions.assertThat(timeInSeconds).isEqualTo(1365);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"aa:bb", "22:88", "22:-45","-22:45"})
    void testUncorrectInput(String argument) {
        Assertions.assertThatThrownBy(()->
            VideoLength.minutesToSeconds(argument)).isInstanceOf(IllegalArgumentException.class);
    }
}

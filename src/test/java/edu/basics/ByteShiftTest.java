package edu.basics;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ByteShiftTest {
    @ParameterizedTest
    @ValueSource(strings = {"8"})
    void byteShift1(Integer argument) {
        // given

        // when
        Integer resultShift = ByteShift.rotateRight(argument, 1);

        // then
        Assertions.assertThat(resultShift).isEqualTo(4);
    }

    @ParameterizedTest
    @ValueSource(strings = {"16"})
    void byteShift2(Integer argument) {
        // given

        // when
        Integer resultShift2 = ByteShift.rotateLeft(argument, 1);

        // then
        Assertions.assertThat(resultShift2).isEqualTo(1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"17"})
    void byteShift3(Integer argument) {
        // given

        // when
        Integer resultShift3 = ByteShift.rotateLeft(argument, 2);

        // then
        Assertions.assertThat(resultShift3).isEqualTo(6);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-5"})
    void testUncorrectInput(Integer argument) {
        Assertions.assertThatThrownBy(() ->
            ByteShift.rotateLeft(argument, 2)).isInstanceOf(IllegalArgumentException.class);
    }

}

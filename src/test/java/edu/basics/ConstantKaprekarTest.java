package edu.basics;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ConstantKaprekarTest {
    @ParameterizedTest
    @ValueSource(strings = {"6621"})
    void countk1(Integer argument) {
        // given

        // when
        Integer countKaprekar = ConstantKaprekar.countK(argument);

        // then
        Assertions.assertThat(countKaprekar).isEqualTo(5);
    }

    @ParameterizedTest
    @ValueSource(strings = {"6554"})
    void countk2(Integer argument) {
        // given

        // when
        Integer countKaprekar2 = ConstantKaprekar.countK(argument);

        // then
        Assertions.assertThat(countKaprekar2).isEqualTo(4);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234"})
    void countk3(Integer argument) {
        // given

        // when
        Integer countKaprekar3 = ConstantKaprekar.countK(argument);

        // then
        Assertions.assertThat(countKaprekar3).isEqualTo(3);
    }

}

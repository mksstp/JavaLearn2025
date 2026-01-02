package edu.basics5;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Task2Test {
    static final Map<LocalDate, LocalDate> NEXT_FRIDAYS = Map.of(
        LocalDate.of(2023, Month.JANUARY,1), LocalDate.of(2023, Month.JANUARY,13),
        LocalDate.of(2023, Month.JANUARY,14), LocalDate.of(2023, Month.OCTOBER,13),
        LocalDate.of(2022, Month.NOVEMBER,14),LocalDate.of(2023, Month.JANUARY,13)
    );

    static Stream<Arguments> generateList() {
        return Stream.of(
            Arguments.of(LocalDate.of(2023, Month.JANUARY,1)),
            Arguments.of(LocalDate.of(2023, Month.JANUARY,14)),
            Arguments.of(LocalDate.of(2022, Month.NOVEMBER,14))
        );
    }

    @ParameterizedTest
    @MethodSource("generateList")
    void testCorrectInput(LocalDate argument) {
        // given
        // when
        LocalDate nextFriday = edu.basics5.Task2.nextFriday13(argument);

        // then
        Assertions.assertThat(nextFriday).isEqualTo(NEXT_FRIDAYS.get(argument));
    }

}

package edu.basics5;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class Task3Test {

    static final Map<String, Optional<LocalDate>> DATE_VERIFICATION_TEMPLATE = Map.of(
        "2020-10-10", Optional.of(LocalDate.of(2020, 10, 10)),
        "2020-12-2", Optional.of(LocalDate.of(2020, 12, 2)),
        "1/3/1976", Optional.of(LocalDate.of(1976, 3, 1)),
        "1/3/20", Optional.of(LocalDate.of(2020, 3, 1)),
        "tomorrow", Optional.of(LocalDate.now().plusDays(1)),
        "today", Optional.of(LocalDate.now()),
        "yesterday", Optional.of(LocalDate.now().minusDays(1)),
        "1 day ago", Optional.of(LocalDate.now().minusDays(1)),
        "2234 days ago", Optional.of(LocalDate.now().minusDays(2234))
    );

    @ParameterizedTest
    @ValueSource(strings = {"2020-10-10", "2020-12-2", "1/3/1976", "1/3/20",
        "tomorrow", "today", "yesterday", "1 day ago", "2234 days ago"})
    void testCorrectInput(String argument) {
        // given
        // when
        Optional<LocalDate> resultValid = Task3.convertStringToDate(argument);
        // then
        Assertions.assertThat(resultValid).isEqualTo(DATE_VERIFICATION_TEMPLATE.get(argument));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testNullAndEmpty(String argument) {
        Assertions.assertThatThrownBy(() ->
            Task3.convertStringToDate(argument)).isInstanceOf(IllegalArgumentException.class);
    }

}

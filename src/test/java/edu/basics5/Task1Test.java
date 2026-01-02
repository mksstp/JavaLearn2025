package edu.basics5;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class Task1Test {

    static final Map<List<String>, String> AVERAGE_TIME = Map.of(
        List.of("2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"), "3ч 40м",
        List.of("2022-04-08, 09:00 - 2022-04-08, 12:30",
            "2022-07-15, 14:45 - 2022-07-15, 18:15",
            "2022-09-20, 20:30 - 2022-09-20, 22:45",
            "2022-11-05, 12:00 - 2022-11-05, 15:30",
            "2022-12-18, 17:15 - 2022-12-18, 20:45"), "3ч 15м",
        List.of("2022-03-12, 23:50 - 2022-03-12, 20:20",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"),"Incorrect time format has been entered",
        List.of("20220312, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"),"Incorrect time format has been entered"
    );

    static Stream<Arguments> generateList() {
        return Stream.of(
            Arguments.of(List.of("2022-03-12, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20")),
            Arguments.of(List.of("2022-04-08, 09:00 - 2022-04-08, 12:30",
                "2022-07-15, 14:45 - 2022-07-15, 18:15",
                "2022-09-20, 20:30 - 2022-09-20, 22:45",
                "2022-11-05, 12:00 - 2022-11-05, 15:30",
                "2022-12-18, 17:15 - 2022-12-18, 20:45")),
            Arguments.of(List.of("2022-03-12, 23:50 - 2022-03-12, 20:20",
                "2022-04-01, 21:30 - 2022-04-02, 01:20")),
            Arguments.of(List.of("20220312, 20:20 - 2022-03-12, 23:50",
                "2022-04-01, 21:30 - 2022-04-02, 01:20"))
        );
    }

    @ParameterizedTest
    @MethodSource("generateList")
    void testCorrectInput(List<String> argument) {
        // given
        // when
        String averageTime = edu.basics5.Task1.averageTime(argument);
        // then
        Assertions.assertThat(averageTime).isEqualTo(AVERAGE_TIME.get(argument));
    }



    @ParameterizedTest
    @NullAndEmptySource
    void testUncorrectInput(List<String> argument) {
        Assertions.assertThatThrownBy(() ->
            Task1.averageTime(argument)).isInstanceOf(IllegalArgumentException.class);
    }

}

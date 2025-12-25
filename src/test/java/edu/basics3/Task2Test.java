package edu.basics3;

import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class Task2Test {

    private static final Map<String, List<String>> CLUSTERIZE_TEST = Map.of(
        "()()()", List.of("()", "()", "()"),
        "((()))", List.of("((()))"), "((()))(())()()(()())", List.of("((()))", "(())", "()", "()", "(()())"),
        "((())())(()(()()))", List.of("((())())", "(()(()()))")
    );

    @ParameterizedTest
    @ValueSource(strings = {"()()()", "((()))", "((()))(())()()(()())", "((())())(()(()()))"})
    void testCorrectInput(String argument) {
        // given

        // when
        List<String> clusterizeList = Task2.clusterize(argument);

        // then
        Assertions.assertThat(clusterizeList).isEqualTo(CLUSTERIZE_TEST.get(argument));
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"()((", "ab", "))()"})
    void testUncorrectInput(String argument) {
        Assertions.assertThatThrownBy(() ->
            Task2.clusterize(argument)).isInstanceOf(IllegalArgumentException.class);
    }

}

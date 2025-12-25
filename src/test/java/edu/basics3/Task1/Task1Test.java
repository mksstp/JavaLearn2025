package edu.basics3.Task1;

import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class Task1Test {

    static Map<String, String> dictionaryAtbash = Map.of("Hello world!", "Svool dliow!",
        "Any fool can write code that a computer can understand. " +
            "Good programmers write code that humans can understand. ― Martin Fowler",
        "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. " +
            "Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi"
    );

    @ParameterizedTest
    @ValueSource(strings = {"Hello world!", "Any fool can write code that a computer can understand. " +
        "Good programmers write code that humans can understand. ― Martin Fowler"})
    void testCorrectInput(String argument) {
        // given

        // when
        String atbashString = Task1.atbash(argument);

        // then
        Assertions.assertThat(atbashString).isEqualTo(dictionaryAtbash.get(argument));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testUncorrectInput(String argument) {
        Assertions.assertThatThrownBy(() ->
            Task1.atbash(argument)).isInstanceOf(IllegalArgumentException.class);
    }

}

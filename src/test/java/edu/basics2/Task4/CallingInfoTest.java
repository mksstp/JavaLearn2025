package edu.basics2.Task4;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CallingInfoTest {

    @Test
    @DisplayName("Проверка корректного вызова callingInfo")
    void correctInput() {
        // given
        //Call callingInfo
        // when
        CallingInfo result = ClassForTest.functionForTest();
        // then
        Assertions.assertThat(result).isEqualTo(new CallingInfo("ClassForTest", "functionForTest"));
    }

}

package edu.basics;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;


class NestableArrayTest {

    @Test
    void testCorrectInput() {
        // given
        List<Integer> testarr1 = List.of(1, 2, 3, 4);
        List<Integer> testarr2 = List.of(0, 6);
        // when
        boolean nestable = NestableArray.isNestable(testarr1,testarr2);

        // then
        Assertions.assertThat(nestable).isEqualTo(true);
    }

    @Test
    void testCorrectInput2() {
        // given
        List<Integer> testarr1 = List.of(3, 1);
        List<Integer> testarr2 = List.of(0, 4);
        // when
        boolean nestable = NestableArray.isNestable(testarr1,testarr2);

        // then
        Assertions.assertThat(nestable).isEqualTo(true);
    }

    @Test
    void testCorrectInput3() {
        // given
        List<Integer> testarr1 = List.of(9, 9, 8);
        List<Integer> testarr2 = List.of(8, 9);
        // when
        boolean nestable = NestableArray.isNestable(testarr1,testarr2);

        // then
        Assertions.assertThat(nestable).isEqualTo(false);
    }

}

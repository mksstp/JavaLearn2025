package edu.basics3;

import java.util.List;
import java.util.NoSuchElementException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BackwardIteratorTest {

    @Test
    @DisplayName("Проверка корректного вызова")
    void correctInput1() {
        //given
        var iterator = new BackwardIterator<>(List.of(1, 2, 3));
        //when
        assertEquals(3, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(1, iterator.next());
        assertFalse(iterator.hasNext());
        //then
        Assertions.assertThatThrownBy(iterator::next).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    @DisplayName("Проверка корректного вызова")
    void correctInput2() {
        //given
        var iterator = new BackwardIterator<>(List.of(1));
        //when
        //then
        assertEquals(1, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Проверка пустого списка")
    void emptyList() {
        //given
        var iterator = new BackwardIterator<Integer>(List.of());
        //when
        //then
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Проверка удаления")
    void testRemove() {
        //given
        var iterator = new BackwardIterator<>(List.of(1));
        //when
        //then
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

}

package edu.basics3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Task7Test {

    @Test
    @DisplayName("Проверка корректного вызова")
    void correctInput() {
        // given
        TreeMap<String, String> tree = new TreeMap<>(new Task7());
        // when
        tree.put(null, "test");
        // then
        assertTrue(tree.containsKey(null));
        // when
        tree.remove(null);
        // then
        assertFalse(tree.containsKey(null));

    }

}

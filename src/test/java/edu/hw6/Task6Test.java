package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class Task6Test {
    @Test
    @DisplayName("Create table")
    void test() {
        var table = Task6.portsCheck();

        assertThat(table).isNotEqualTo(null);
    }
}

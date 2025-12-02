package edu.hw7.Task1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IncrementerTest {

    @Test
    void incrementerTest1() {
        Incrementer incrementer = new Incrementer(0);

        incrementer.multiThreadIncrement(5, 10);

        assertEquals(50, incrementer.get());
    }

    @Test
    void incrementerTest2() {
        Incrementer incrementer = new Incrementer(0);

        incrementer.multiThreadIncrement(1, 10);

        assertEquals(10, incrementer.get());
    }

}

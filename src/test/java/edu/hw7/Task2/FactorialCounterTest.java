package edu.hw7.Task2;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCounterTest {

    @Test
    void factorialTest1() {
        assertEquals(BigInteger.valueOf(1), FactorialCounter.factorial(0));
        assertEquals(BigInteger.valueOf(1), FactorialCounter.factorial(1));
    }

    @Test
    void factorialTest2() {
        assertEquals(BigInteger.valueOf(3628800), FactorialCounter.factorial(10));
    }
}

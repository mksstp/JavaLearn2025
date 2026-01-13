package edu.basics7.Task2;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MultiThreadFactorialTest {

    @Test
    void factorialTest1() {
        assertEquals(BigInteger.valueOf(1), MultiThreadFactorial.calculate(0));
        assertEquals(BigInteger.valueOf(1), MultiThreadFactorial.calculate(1));
    }

    @Test
    void factorialTest2() {
        assertEquals(BigInteger.valueOf(3628800), MultiThreadFactorial.calculate(10));
    }

}

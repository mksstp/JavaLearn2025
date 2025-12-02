package edu.hw7.Task2;

import java.math.BigInteger;
import java.util.stream.IntStream;

public class FactorialCounter {

    private FactorialCounter() {
    }

    public static BigInteger factorial(int value) {
        return IntStream.range(1, value + 1)
            .mapToObj(BigInteger::valueOf)
            .parallel()
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}

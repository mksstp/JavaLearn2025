package edu.basics7.Task2;

import java.math.BigInteger;
import java.util.stream.IntStream;

public final class MultiThreadFactorial {
    private MultiThreadFactorial() {
    }

    public static BigInteger calculate(int value) {
        return IntStream.range(1, value + 1)
            .mapToObj(BigInteger::valueOf)
            .parallel()
            .reduce(BigInteger.ONE, BigInteger::multiply);
    }
}

package edu.basics;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ConstantKaprekar {
    private ConstantKaprekar() {
    }

    private static final int THOUSAND = 1000;
    private static final int TENTHOUSAND = 10000;
    private static final int NUMBERKAPREKAR = 6174;
    private static final List<Integer> SIMILARDIGITS = List.of(1111, 2222, 3333, 4444, 5555, 6666, 7777, 8888);
    private static final String UNCORRECT_INPUT = "Введено некорректное число";

    private static Boolean checkNumber(Integer number) {
        return !SIMILARDIGITS.contains(number) && number > THOUSAND && number < TENTHOUSAND;
    }

    private static Integer stepKaprekar(Integer number) {

        StringBuilder sortNumber = new StringBuilder(Stream
            .of(number.toString().split(""))
            .sorted()
            .collect(Collectors.joining()));
        Integer rightNumber = Integer.parseInt(sortNumber.toString());
        Integer leftNumber = Integer.parseInt(sortNumber.reverse().toString());
        return leftNumber - rightNumber;
    }

    public static Integer countK(Integer startNumber) {
        if (!checkNumber(startNumber)) {
            throw new IllegalArgumentException(UNCORRECT_INPUT);
        }
        Integer countK = 0;
        Integer number = startNumber;
        while (!number.equals(NUMBERKAPREKAR)) {
            countK++;
            number = stepKaprekar(number);
        }
        return countK;

    }
}

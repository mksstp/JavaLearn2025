package edu.basics3;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class Task4 {
    private Task4() {
    }

    private final static int MAX_VALUE = 3999;
    private final static int MIN_VALUE = 1;

    @SuppressWarnings("MagicNumber")
    private static final LinkedHashMap<String, Integer> ROMAN_ARAB_DICT = new LinkedHashMap<>() {
        {
            put("M", 1000);
            put("CM", 900);
            put("D", 500);
            put("CD", 400);
            put("C", 100);
            put("XC", 90);
            put("L", 50);
            put("XL", 40);
            put("X", 10);
            put("IX", 9);
            put("V", 5);
            put("IV", 4);
            put("I", 1);
        }
    };

    public static String convertToRoman(Integer number) {
        if (number == null || number < MIN_VALUE || number > MAX_VALUE) {
            throw new IllegalArgumentException("Некорректное число!");
        }
        var currNumber = number;
        List<String> romanList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : ROMAN_ARAB_DICT.entrySet()) {
            Integer value = entry.getValue();
            while (currNumber >= value) {
                romanList.add(entry.getKey());
                currNumber = currNumber - value;
            }
        }
        return String.join("", romanList);
    }
}

package edu.basics3;

import java.util.HashMap;
import java.util.List;

public final class Task3 {
    private Task3() {
    }

    public static HashMap<?, Integer> freqDict(List<?> list) {
        if ((list == null) || list.isEmpty()) {
            throw new IllegalArgumentException("Введен некорректный список");
        }
        HashMap<Object, Integer> dict = new HashMap<>();
        for (Object message : list) {
            if (dict.containsKey(message)) {
                dict.put(message, dict.get(message) + 1);
            } else {
                dict.put(message, 1);
            }
        }
        return dict;
    }
}

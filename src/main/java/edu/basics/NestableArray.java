package edu.basics;

import java.util.List;

public final class NestableArray {
    private NestableArray() {
    }

    private static List<Integer> getMaxMin(List<Integer> arr) {
        Integer max = arr.getFirst();
        Integer min = arr.getFirst();
        for (Integer number : arr) {
            if (number > max) {
                max = number;
            }
            if (number < min) {
                min = number;
            }
        }
        return List.of(max, min);
    }

    public static boolean isNestable(List<Integer> firstArr, List<Integer> secondArr) {
        var maxMinFirstArr = getMaxMin(firstArr);
        var maxMinSecondArr = getMaxMin(secondArr);
        return (maxMinFirstArr.get(0) < maxMinSecondArr.get(0)) && (maxMinFirstArr.get(1) > maxMinSecondArr.get(1));
    }
}

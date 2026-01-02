package edu.basics5;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public final class Task2 {
    private Task2() {
    }

    private static final int DAY_FOR_SEARCH = 13;

    public static List<LocalDate> findAllFridays13InYear(Integer year) {
        List<LocalDate> listFridays13InYear = new ArrayList<>();
        for (Month currentMonth : Month.values()) {
            if (LocalDate.of(year, currentMonth, DAY_FOR_SEARCH).getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                listFridays13InYear.add(LocalDate.of(year, currentMonth, DAY_FOR_SEARCH));
            }
        }
        return listFridays13InYear;
    }

    public static LocalDate nextFriday13(LocalDate datePoint) {
        List<LocalDate> listFridays13 = findAllFridays13InYear(datePoint.getYear());
        if (listFridays13.getLast().isBefore(datePoint)) {
            listFridays13 = findAllFridays13InYear(datePoint.plusYears(1).getYear());
        }
        for (LocalDate currentFriday : listFridays13) {
            if (currentFriday.isAfter(datePoint)) {
                return currentFriday;
            }
        }
        throw new RuntimeException();
    }
}

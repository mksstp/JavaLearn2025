package edu.basics5;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public final class Task1 {
    private Task1() {
    }

    private static final int MINUTES_IN_HOUR = 60;
    private static final String PATTERN_FOR_TIME = "yyyy-MM-dd, HH:mm";
    private static final String SEPARATOR = " - ";
    private static final String INPUT_ILLEGAL_TIME_FORMAT = "Incorrect time format has been entered";

    public static String averageTime(List<String> processedListTime) {
        if ((processedListTime == null) || processedListTime.isEmpty()) {
            throw new IllegalArgumentException("INPUT_ILLEGAL_TIME_FORMAT");
        }
        Duration sumOfTime = Duration.ofDays(0);
        for (String entryTime : processedListTime) {
            String startTimeString = entryTime.split(SEPARATOR)[0];
            String endTimeString = entryTime.split(SEPARATOR)[1];
            LocalDateTime startTime;
            LocalDateTime endTime;
            try {
                startTime = LocalDateTime.parse(startTimeString, DateTimeFormatter.ofPattern(PATTERN_FOR_TIME));
                endTime = LocalDateTime.parse(endTimeString, DateTimeFormatter.ofPattern(PATTERN_FOR_TIME));
            } catch (DateTimeException e) {
                return INPUT_ILLEGAL_TIME_FORMAT;
            }
            if (startTime.isAfter(endTime)) {
                return INPUT_ILLEGAL_TIME_FORMAT;
            }
            sumOfTime = sumOfTime.plus(Duration.between(startTime, endTime));
        }
        Duration averageTime = sumOfTime.dividedBy(processedListTime.size());
        return averageTime.toHours() + "ч " + averageTime.toMinutes() % MINUTES_IN_HOUR + "м";
    }
}

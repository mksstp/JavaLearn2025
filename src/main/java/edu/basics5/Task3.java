package edu.basics5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task3 {
    private Task3() {
    }

    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final String YYYY_MM_D = "yyyy-MM-d";
    private static final String D_M_YYYY = "d/M/yyyy";
    private static final String D_M_YY = "d/M/yy";
    private static final String TOMORROW = "tomorrow";
    private static final String TODAY = "today";
    private static final String YESTERDAY = "yesterday";

    private static final String PATTERN_DAYS_AGO = "^([0-9]+) (days|day) ago$";
    private static final String EMPTY = "empty";


    private static final Map<String, String> MAP_PATTERNS = new HashMap<>() {
        {
            put("^\\d{4}-(0[1-9]|1[012])-(1\\d|2\\d|3[01])$", YYYY_MM_DD);
            put("^\\d{4}-(0[1-9]|1[012])-([1-9])$", YYYY_MM_D);
            put("^([1-9]|1\\d|2\\d|3[01])/([1-9]|1[012])/\\d{4}$", D_M_YYYY);
            put("^([1-9]|1\\d|2\\d|3[01])/([1-9]|1[012])/\\d{2}$", D_M_YY);
            put("^tomorrow$", TOMORROW);
            put("^today$", TODAY);
            put("^yesterday$", YESTERDAY);
            put(PATTERN_DAYS_AGO, PATTERN_DAYS_AGO);
        }
    };

    public static String definePattern(String processedString) {
        for (String format : MAP_PATTERNS.keySet()) {
            if (processedString.matches(format)) {
                return MAP_PATTERNS.get(format);
            }
        }
        return EMPTY;
    }

    public static Optional<LocalDate> convertStringToDate(String inputString) {
        if (inputString == null || inputString.isBlank()) {
            throw new IllegalArgumentException();
        }
        return switch (definePattern(inputString)) {
            case YYYY_MM_DD -> Optional.of(LocalDate.parse(inputString, DateTimeFormatter.ofPattern(YYYY_MM_DD)));
            case YYYY_MM_D -> Optional.of(LocalDate.parse(inputString, DateTimeFormatter.ofPattern(YYYY_MM_D)));
            case D_M_YYYY -> Optional.of(LocalDate.parse(inputString, DateTimeFormatter.ofPattern(D_M_YYYY)));
            case D_M_YY -> Optional.of(LocalDate.parse(inputString, DateTimeFormatter.ofPattern(D_M_YY)));
            case TOMORROW -> Optional.of(LocalDate.now().plusDays(1));
            case TODAY -> Optional.of(LocalDate.now());
            case YESTERDAY -> Optional.of(LocalDate.now().minusDays(1));
            case PATTERN_DAYS_AGO -> {
                Matcher matcherDate = Pattern.compile(PATTERN_DAYS_AGO).matcher(inputString);
                if (matcherDate.matches()) {
                    yield Optional.of(LocalDate.now().minusDays(Long.parseLong(matcherDate.group(1))));
                }
                throw new RuntimeException();
            }
            default -> Optional.empty();
        };
    }

}

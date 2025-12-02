package edu.project3.logReaders;

import edu.project3.shared.LogRecord;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputStreamLogReader implements LogReader {

    @SuppressWarnings("MagicNumber")
    private static LogRecord getLogRecordFromLine(String log) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("^(.*) - (.*) \\[(.*)\\] \"(.*)\" ([0-9]*) ([0-9]*) \"(.*)\" \"(.*)\"$");
        Matcher matcher = pattern.matcher(log);
        if (!matcher.find()) {
            throw new IllegalArgumentException();
        }

        // Matching arguments
        String remoteAddr = matcher.group(1);
        String remoteUser = matcher.group(2);
        String dateTime = matcher.group(3);
        String requestBody = matcher.group(4);
        String status = matcher.group(5);
        String bytes = matcher.group(6);
        String httpReferer = matcher.group(7);
        String httpUserAgent = matcher.group(8);

        // Adding LogRecord to list
        int intStatus = Integer.parseInt(status);
        int intBytes = Integer.parseInt(bytes);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTime, formatter);

        return new LogRecord(
            remoteAddr,
            remoteUser,
            offsetDateTime,
            requestBody,
            intStatus,
            intBytes,
            httpReferer,
            httpUserAgent
        );
    }

    public List<LogRecord> read(InputStream inputStream) throws IOException, IllegalArgumentException {
        List<LogRecord> logRecords = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

            String log;
            while ((log = bufferedReader.readLine()) != null) {
                logRecords.add(getLogRecordFromLine(log));
            }

        } catch (IllegalArgumentException | DateTimeException e) {
            throw new IllegalArgumentException("Wrong log string");
        }

        return logRecords;
    }

}

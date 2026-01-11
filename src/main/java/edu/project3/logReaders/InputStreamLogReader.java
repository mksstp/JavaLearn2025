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

public final class InputStreamLogReader implements LogReaderInterface {

    @SuppressWarnings("MagicNumber")
    private static LogRecord getLogRecordFromLine(String log) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile("^(.*) - (.*) \\[(.*)\\] \"(.*)\" ([0-9]*) ([0-9]*) \"(.*)\" \"(.*)\"$");
        Matcher mathcer = pattern.matcher(log);
        if (!mathcer.find()) {
            throw new IllegalArgumentException();
        }
        String remoteAddr = mathcer.group(1);
        String remoteUser = mathcer.group(2);
        String dateTime = mathcer.group(3);
        String requestBody = mathcer.group(4);
        String status = mathcer.group(5);
        String bytes = mathcer.group(6);
        String httpReferer = mathcer.group(7);
        String httpsUserAgent = mathcer.group(8);

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
            httpsUserAgent
        );
    }

    @Override
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

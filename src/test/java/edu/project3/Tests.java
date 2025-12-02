package edu.project3;

import edu.project3.logReaders.InputStreamLogReader;
import edu.project3.shared.LogRecord;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import edu.project3.tableRenderers.MarkdownTableRenderer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {

    @Test
    void inputStreamLogReaderTest() {
        InputStreamLogReader inputStreamLogReader = new InputStreamLogReader();
        InputStream inputStream =
            new ByteArrayInputStream(("93.180.71.3 - - [12/May/2015:08:05:32 +0200] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)\"\n").getBytes());
        List<LogRecord> expected = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.of(2015, 5, 12, 8, 5, 32);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(2));

        expected.add(new LogRecord(
            "93.180.71.3",
            "-",
            offsetDateTime,
            "GET /downloads/product_1 HTTP/1.1",
            304,
            0,
            "-",
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
        ));

        assertDoesNotThrow(() -> {
            List<LogRecord> logs = inputStreamLogReader.read(inputStream);
            assertEquals(expected, logs);
        });
    }

    @Test
    void loggerStatisticsMachineTest() {
        // Arrange
        LoggerStatisticsMachine loggerStatisticsMachine = new LoggerStatisticsMachine("test.txt", null, null);
        List<LogRecord> logs = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.of(2015, 5, 12, 8, 5, 32);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.ofHours(2));
        logs.add(new LogRecord(
            "93.180.71.3",
            "-",
            offsetDateTime,
            "GET /downloads/product_1 HTTP/1.1",
            304,
            0,
            "-",
            "Debian APT-HTTP/1.3 (0.8.16~exp12ubuntu10.21)"
        ));
        loggerStatisticsMachine.addLogs(logs);

        // Assert 1
        assertDoesNotThrow(() -> {
            // Act 2
            loggerStatisticsMachine.renderToFile(
                "src/main/resources/project3/test2result.md",
                new MarkdownTableRenderer()
            );
            byte[] file1Bytes = Files.readAllBytes(Paths.get("src/main/resources/project3/test2result.md"));
            byte[] file2Bytes = Files.readAllBytes(Paths.get("src/main/resources/project3/test2expected.md"));
            String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
            String file2 = new String(file2Bytes, StandardCharsets.UTF_8);
            file2 = file2.replaceAll("\r", "");
            file1 = file1.replaceAll("\r", "");

            // Assert 2
            assertEquals(file1, file2);
        });
    }

    @Test
    void mainTest() {
        String[] args = new String[]{"--path", "test.txt", "--format", "adoc", "--to", "2015-05-14"};

        Main.main(args);

        assertDoesNotThrow(() -> {
            // Act 2
            byte[] file1Bytes = Files.readAllBytes(Paths.get("src/main/resources/project3/test3expected.adoc"));
            byte[] file2Bytes = Files.readAllBytes(Paths.get("src/main/resources/project3/result.adoc"));
            String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
            String file2 = new String(file2Bytes, StandardCharsets.UTF_8);
            file2 = file2.replaceAll("\r", "");
            file1 = file1.replaceAll("\r", "");

            // Assert 2
            assertEquals(file1, file2);
        });
    }
}

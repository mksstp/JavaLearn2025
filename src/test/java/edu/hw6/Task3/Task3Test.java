package edu.hw6.Task3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task3Test {
    @Test
    @DisplayName("andCheck")
    void andCheckTest() throws IOException {
        try (PrintWriter pw = new PrintWriter("foo", StandardCharsets.UTF_8)) {
            pw.print(100500);
        }
        DirectoryStream.Filter<Path> filter = Task3.READABLE
            .and(Task3.WRITABLE)
            .and(Task3.largerThan(5))
            .and(Task3.fileName("foo"));
        ArrayList<String> files = new ArrayList<>();
        ArrayList<String> ansFiles = new ArrayList<>(List.of("foo"));


        try (DirectoryStream<Path> entries = Files.newDirectoryStream(Path.of("."), filter)) {
            entries.forEach(file -> files.add(file.getFileName().toString()));
        }

        try {
            assertThat(files).isEqualTo(ansFiles);
        } finally {
            Files.deleteIfExists(Path.of("foo"));
        }
    }

}

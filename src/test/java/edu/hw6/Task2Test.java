package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class Task2Test {

    private static ArrayList<String> entryFiles(Path dir) throws IOException {
        ArrayList<String> ret = new ArrayList<>();
        try (Stream<Path> dirStream = Files.list(dir)) {
            dirStream.forEach((Path curPath) -> ret.add(curPath.getFileName().toString()));
        }

        return ret;
    }

    private static void deleteOneLvlDir(Path dir) throws IOException {
        var entry = entryFiles(dir);

        for (String file : entry) {
            Files.deleteIfExists(Path.of(dir.toString(), file));
        }

        Files.delete(dir);
    }

    @Test
    @DisplayName("simpleCopy")
    void simpleCopyTest() throws IOException {
        try {
            Files.createDirectory(Path.of("simpleCopy"));
            Files.createFile(Path.of("simpleCopy", "foo.txt"));
            ArrayList<String> ans = new ArrayList<>(List.of(
                "foo.txt",
                "foo - копия.txt"
            ));
            Collections.sort(ans);

            Task2.cloneFile(Path.of("simpleCopy", "foo.txt"));
            var entry = entryFiles(Path.of("simpleCopy"));
            Collections.sort(entry);

            assertThat(entry).isEqualTo(ans);
        } finally {
            deleteOneLvlDir(Path.of("simpleCopy"));
        }
    }

    @Test
    @DisplayName("doubleCopy")
    void doubleCopyTest() throws IOException {
        try {
            Files.createDirectory(Path.of("doubleCopy"));
            Files.createFile(Path.of("doubleCopy", "foo.txt"));
            ArrayList<String> ans = new ArrayList<>(List.of(
                "foo.txt",
                "foo - копия.txt",
                "foo - копия (1).txt"
            ));
            Collections.sort(ans);

            Task2.cloneFile(Path.of("doubleCopy", "foo.txt"));
            Task2.cloneFile(Path.of("doubleCopy", "foo.txt"));
            var entry = entryFiles(Path.of("doubleCopy"));
            Collections.sort(entry);

            assertThat(entry).isEqualTo(ans);
        } finally {
            deleteOneLvlDir(Path.of("doubleCopy"));
        }
    }

}

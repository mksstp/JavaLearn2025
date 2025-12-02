package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.Adler32;
import java.util.zip.Checksum;
import static org.junit.jupiter.api.Assertions.*;

class Task4Test {

    @Test
    @DisplayName("Correct call")
    void test() throws IOException {
        String toWrite = "Programming is learned by writing programs. ― Brian Kernighan";
        byte[] bytes = toWrite.getBytes();
        Checksum checksum = new Adler32();
        checksum.update(bytes, 0, bytes.length);
        long excepted = checksum.getValue();

        long cur = Task4.printGenious(Path.of("foo"), new Adler32());
        Files.deleteIfExists(Path.of("foo"));

        assertEquals(cur, excepted);
    }

}

package edu.hw6;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

@SuppressWarnings("NestedTryDepth")
public final class Task4 {
    private Task4() {
    }

    public static long printGenious(Path file, Checksum checksum) throws IOException {
        long value;

        try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE_NEW)) {
            String strToWrite = "Programming is learned by writing programs. ― Brian Kernighan";

            try (CheckedOutputStream cos = new CheckedOutputStream(os, checksum)) {
                try (BufferedOutputStream bos = new BufferedOutputStream(cos, strToWrite.length())) {
                    try (OutputStreamWriter osw = new OutputStreamWriter(bos, StandardCharsets.UTF_8)) {
                        try (PrintWriter pw = new PrintWriter(osw)) {
                            pw.print(strToWrite);
                        }
                    }
                }

                value = cos.getChecksum().getValue();
            }
        }

        return value;
    }
}

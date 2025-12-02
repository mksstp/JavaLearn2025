package edu.hw6.Task1;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DiskMap implements Map<String, String> {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Path filePath;
    private int sizeOfMap = 0;
    private static final String EXCEPTION_MESSAGE = "IOException has been called";

    public DiskMap() {
        filePath = Paths.get("src/main/resources/hw6/diskmap.txt");
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
            Files.createFile(filePath);
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
        }
    }

    @Override
    public int size() {
        return sizeOfMap;
    }

    @Override
    public boolean isEmpty() {
        try {
            var file = Files.readAllLines(filePath);
            return file.isEmpty();
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean containsKey(Object key) {
        try {
            List<String> allLines = Files.readAllLines(filePath);
            return allLines.stream()
                .map(line -> line.split(":"))
                .anyMatch(str -> str[0].equals(key.toString()));
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
            return false;
        }
    }

    @Override
    public boolean containsValue(Object value) {
        try {
            List<String> allLines = Files.readAllLines(filePath);
            return allLines.stream()
                .map(line -> line.split(":"))
                .anyMatch(str -> str[1].equals(value.toString()));
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
            return false;
        }
    }

    @Override
    public String get(Object key) {
        try {
            List<String> allLines = Files.readAllLines(filePath);
            return allLines.stream()
                .map(line -> line.split(":"))
                .filter(str -> str[0].equals(key.toString()))
                .findFirst()
                .get()[1];
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
            return null;
        }
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        sizeOfMap++;
        String returnedValue = value;
        try {
            if (containsKey(key)) {
                returnedValue = get(key);
            }
            Files.write(filePath, (key + ':' + value + '\n').getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
        }
        return returnedValue;
    }

    @Override
    public String remove(Object key) {
        sizeOfMap--;
        String returnedValue;
        try {
            returnedValue = get(key);
            List<String> lines = Files.readAllLines(filePath);
            lines.removeIf(line -> line.split(":")[0].equals(key));
            Files.write(filePath, lines);
            return returnedValue;
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
        }
        return null;
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        for (Map.Entry<? extends String, ? extends String> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        sizeOfMap = 0;
        try {
            FileChannel.open(filePath, StandardOpenOption.WRITE).truncate(0).close();
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
        }
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        try (var linesForSet = Files.lines(filePath)) {
            return linesForSet
                .map(line -> line.split(":")[0])
                .collect(Collectors.toSet());
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
            return Collections.emptySet();
        }
    }

    @NotNull
    @Override
    public Collection<String> values() {
        try (var linesForSet = Files.lines(filePath)) {
            return linesForSet
                .map(line -> line.split(":")[1])
                .collect(Collectors.toSet());
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
            return Collections.emptySet();
        }
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        try (var linesForSet = Files.lines(filePath)) {
            return linesForSet
                .map(line -> new AbstractMap
                    .SimpleEntry<>(line.split(":")[0], line.split(":")[1]))
                .collect(Collectors.toSet());
        } catch (IOException e) {
            LOGGER.info(EXCEPTION_MESSAGE);
            return Collections.emptySet();
        }
    }
}

package edu.hw6.Task3;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task3 {
    private Task3() {
    }

    public static AbstractFilter largerThan(int size) {
        return entry -> Files.size(entry) > size;
    }

    public static AbstractFilter magicNumber(char... magics) {
        return entry -> {
            if (!largerThan(magics.length).accept(entry)) {
                return false;
            }

            ByteBuffer bf = ByteBuffer.allocate(magics.length);
            FileChannel inChannel = FileChannel.open(entry);
            inChannel.read(bf);
            ArrayList<Character> readed = new ArrayList<>();
            while (bf.hasRemaining()) {
                readed.add((char) bf.get());
            }
            inChannel.close();

            ArrayList<Character> toCheck = new ArrayList<>();
            for (char cur : magics) {
                toCheck.add(cur);
            }

            return readed.equals(toCheck);
        };
    }

    public static AbstractFilter extension(String ext) {
        return entry -> {
            var splitted = entry.getFileName().toString().split("\\,");
            if (splitted.length < 2) {
                return false;
            }

            return splitted[1].equals(ext);
        };
    }

    public static final AbstractFilter READABLE = Files::isReadable;
    public static final AbstractFilter WRITABLE = Files::isWritable;

    public static AbstractFilter fileName(String regex) {
        return entry -> {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(entry.getFileName().toString());
            return matcher.matches();
        };
    }
}

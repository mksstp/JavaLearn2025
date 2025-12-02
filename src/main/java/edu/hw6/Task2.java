package edu.hw6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@SuppressWarnings("MultipleStringLiterals")
public final class Task2 {
    private Task2() {
    }

    private static void createFileCopy(Path file, int copyNumber) throws IOException {
        String fileNameOnly = file.getFileName().toString().split("\\.")[0];
        String fileExtension = file.getFileName().toString().split("\\.")[1];

        StringBuilder newFileName = new StringBuilder();
        newFileName.append(fileNameOnly);
        newFileName.append(" - копия");

        if (copyNumber > 0) {
            newFileName.append(" (").append(copyNumber).append(")");
        }

        newFileName.append(".");
        newFileName.append(fileExtension);

        Files.createFile(Path.of(file.getParent().toString(), newFileName.toString()));
    }


    public static void cloneFile(Path file) throws IOException {
        String fileNameOnly = file.getFileName().toString().split("\\.")[0];
        String fileExtension = file.getFileName().toString().split("\\.")[1];

        ArrayList<Path> clonesList;
        try (Stream<Path> filesS = Files.list(file.getParent())) {
            clonesList = new ArrayList<>(filesS
                .filter((Path curFile) -> {
                    Pattern pattern = Pattern.compile(fileNameOnly + " - копия( \\(\\d*\\))?\\." + fileExtension);
                    Matcher matcher = pattern.matcher(curFile.getFileName().toString());
                    return matcher.matches();
                })
                .toList());
        }

        Collections.sort(clonesList);
        if (clonesList.isEmpty()) {
            createFileCopy(file, 0);
        } else {
            if (clonesList.getLast().getFileName().toString().endsWith(")" + fileExtension)) {
                String numberEnd = clonesList.getLast().getFileName().toString().split(")" + fileExtension)[0];

                int number = Integer.parseInt(numberEnd.substring(numberEnd.lastIndexOf('(') + 1));

                createFileCopy(file, number + 1);
            } else {
                createFileCopy(file, 1);
            }
        }
    }
}

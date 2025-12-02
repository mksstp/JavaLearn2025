package edu.hw9.Task2;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Поиск папок")
    void findingDirectories() {
        // given
        String testString = "src/main/resources/hw9";
        File test = new File(testString);
        int numberOfFiles = 2;
        List<File> answer;

        // when
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            DirectoryFinder finder = new DirectoryFinder(test, numberOfFiles);
            answer = forkJoinPool.invoke(finder);
        }
        answer.sort(Comparator.naturalOrder());

        // then
        assertThat(answer.size()).isEqualTo(2);
        assertThat(answer.get(0).getName()).isEqualTo("01");
    }

    @Test
    @DisplayName("Поиск файла по размеру и расширению")
    void extensionAndSize() {
        // given
        String testString = "src/main/resources/hw9";
        File test = new File(testString);
        //implicit size indication because git compresses the file
        long size = new File("src/main/resources/hw9/Task2/01/123.txt").length();
        String extension = ".txt";
        List<File> answer;

        // when
        try (ForkJoinPool forkJoinPool = new ForkJoinPool()) {
            PredicateFinder finder = new PredicateFinder(test, size, extension);
            answer = forkJoinPool.invoke(finder);
        }

        // then
        assertThat(answer.size()).isEqualTo(1);
        assertThat(answer.get(0).getName()).isEqualTo("123.txt");
    }
}

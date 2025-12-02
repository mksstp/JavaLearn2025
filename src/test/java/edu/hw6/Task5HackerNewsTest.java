package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Task5HackerNewsTest {
    @Test
    @DisplayName("Correct call")
    void test() {
        var topicsNumbers = Task5HackerNews.hackerNewsTopStories();

        assertThat(topicsNumbers).isNotEqualTo(null);
    }

    @Test
    @DisplayName("News name")
    void testName() {
        var topicTitle = Task5HackerNews.news(37570037);
        String exp = "JDK 21 Release Note";

        assertThat(topicTitle).isEqualTo(exp);
    }
}
